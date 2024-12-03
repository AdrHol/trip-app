package org.holubecky.adapters.out.web.geocoding;


import lombok.RequiredArgsConstructor;
import org.holubecky.adapters.out.web.dto.ForwardGeocodingApiResponse;
import org.holubecky.adapters.out.web.dto.ReverseGeocodingResponse;
import org.holubecky.adapters.out.web.geocoding.feignClient.GeocodingClient;
import org.holubecky.application.domain.entities.Location;
import org.holubecky.application.ports.out.web.GeoCodingPort;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class GeocodingAdapter implements GeoCodingPort {

    private final GeocodingClient geocodingClient;
    private final String API = "xxxx";

    @Override
    public Location getLocationByCoordinates(String lon, String lat) {
        ReverseGeocodingResponse response = geocodingClient.reverseGeocodingRequest(API, lat, lon, "json");

        return null;
    }

    @Override
    public Location getCoordinatesByLocation(String city, String country) {
        String queue = String.format("%s, %s", city, country);
        List<ForwardGeocodingApiResponse> response = geocodingClient.geocodingRequest(API, queue, "json");

        return null;
    }

    private void mapForwardGeocodingToLocation(){
    }
    private void mapReverseGeocodingToLocation(){
    }
}
