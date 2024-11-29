package com.example.trip_app.prices_service.adapters.out.web.geocoding;

import com.example.trip_app.prices_service.adapters.out.web.dto.ForwardGeocodingApiResponse;
import com.example.trip_app.prices_service.adapters.out.web.dto.ReverseGeocodingResponse;
import com.example.trip_app.prices_service.adapters.out.web.geocoding.feignClient.GeocodingClient;
import com.example.trip_app.prices_service.application.domain.entities.Location;
import com.example.trip_app.prices_service.application.ports.out.web.GeoCodingPort;
import lombok.RequiredArgsConstructor;
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
