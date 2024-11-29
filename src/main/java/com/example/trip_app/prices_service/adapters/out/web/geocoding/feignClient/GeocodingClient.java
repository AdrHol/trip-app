package com.example.trip_app.prices_service.adapters.out.web.geocoding.feignClient;

import com.example.trip_app.prices_service.adapters.out.web.dto.ForwardGeocodingApiResponse;
import com.example.trip_app.prices_service.adapters.out.web.dto.ReverseGeocodingResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(name = "geocodingClient", url = "https://us1.locationiq.com/v1")
public interface GeocodingClient {

    @GetMapping("/reverse")
    ReverseGeocodingResponse reverseGeocodingRequest(@RequestParam("key") String apiKey, @RequestParam("lat") String latitude,
                                                     @RequestParam("lon") String longitude,
                                                     @RequestParam(value = "format", defaultValue = "json") String format);
    @GetMapping("/search")
    List<ForwardGeocodingApiResponse> geocodingRequest(@RequestParam("key") String apiKey, @RequestParam("q") String searchQueue,
                                                       @RequestParam(value = "format", defaultValue = "json") String format);
}
