package org.holubecky.adapters.out.web.geocoding;


import lombok.RequiredArgsConstructor;
import org.holubecky.adapters.out.web.dto.ForwardGeocodingApiResponse;
import org.holubecky.adapters.out.web.dto.ReverseGeocodingResponse;
import org.holubecky.adapters.out.web.geocoding.feignClient.GeocodingClient;
import org.holubecky.application.domain.entities.Location;
import org.holubecky.application.domain.exceptions.LocationNotFoundException;
import org.holubecky.application.ports.out.web.GeoCodingPort;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class GeocodingAdapter implements GeoCodingPort {

    private final GeocodingClient geocodingClient;
    private final String API = "secret";

    @Override
    public Location getLocationByCoordinates(String lon, String lat) {
        ReverseGeocodingResponse response = geocodingClient.reverseGeocodingRequest(API, lat, lon, "json");

        if(response == null){
            throw new LocationNotFoundException();
        }

        return mapReverseGeocodingToLocation(response);
    }

    @Override
    public Location getCoordinatesByLocation(String city, String country) {
        String queue = String.format("%s, %s", city, country);
        List<ForwardGeocodingApiResponse> response = geocodingClient.geocodingRequest(API, queue, "json");
        if(response.size() < 1) {
            throw new LocationNotFoundException();
        }
        return mapForwardGeocodingToLocation(response.get(0), city, country);
    }

    private Location mapForwardGeocodingToLocation(ForwardGeocodingApiResponse response, String city, String country){
        Location result = new Location();
        result.setCity(city);
        result.setCountry(country);
        result.setLatitude(response.lat());
        result.setLongitude(response.lon());

        return result;
    }
    private Location mapReverseGeocodingToLocation(ReverseGeocodingResponse response){
        Location result = new Location();
        String city = response.address().city() == null ? response.address().town() : response.address().city();

        result.setCity(city);
        result.setCountry(response.address().country());
        result.setLatitude(response.lat());
        result.setLongitude(response.lon());
        return result;
    }
}
