package org.holubecky.adapters.out.web.geocoding;

import lombok.RequiredArgsConstructor;
import org.holubecky.adapters.out.web.geocoding.dto.ForwardGeocodingApiResponse;
import org.holubecky.adapters.out.web.geocoding.dto.ReverseGeocodingApiResponse;
import org.holubecky.adapters.out.web.geocoding.exceptions.LocationNotFoundException;
import org.holubecky.adapters.out.web.geocoding.feignClient.LocationiqClient;

import org.holubecky.application.ports.out.web.geocoding.GeoCodingPort;
import org.holubecky.application.ports.out.web.geocoding.dto.Location;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class GeocodingAdapter implements GeoCodingPort {

    private final LocationiqClient geocodingClient;
    private final String API = "";

    @Override
    public Location getLocationByCoordinates(Double lon, Double lat) {
        ReverseGeocodingApiResponse response = geocodingClient.reverseGeocodingRequest(API, String.valueOf(lat), String.valueOf(lon), "json");

        if(response == null){
            throw new LocationNotFoundException();
        }

        return mapReverseGeocodingToLocation(response);
    }

    @Override
    public Location getCoordinatesByLocation(String city, String country) {
        String queue = String.format("%s, %s", city, country);
        List<ForwardGeocodingApiResponse> response = geocodingClient.geocodingRequest(API, queue, "json");
        if(response.isEmpty()) {
            throw new LocationNotFoundException();
        }
        return mapForwardGeocodingToLocation(response.getFirst(), city, country);
    }

    private Location mapForwardGeocodingToLocation(ForwardGeocodingApiResponse response, String city, String country){
        Location result = new Location();
        result.setCity(city);
        result.setCountry(country);
        result.setLat(response.lat());
        result.setLon(response.lon());
        return result;
    }
    private Location mapReverseGeocodingToLocation(ReverseGeocodingApiResponse response){
        Location result = new Location();
        String city = response.address().city() == null ? response.address().town() : response.address().city();
        result.setCity(city);
        result.setCountry(response.address().country());
        result.setLat(response.lat());
        result.setLon(response.lon());
        return result;
    }
}
