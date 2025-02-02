package org.holubecky.adapters.out.web.geocoding.feignClient;

import org.holubecky.adapters.out.web.geocoding.dto.ForwardGeocodingApiResponse;
import org.holubecky.adapters.out.web.geocoding.dto.ReverseGeocodingApiResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(name = "geocodingClient", url = "https://us1.locationiq.com/v1")
public interface LocationiqClient {
    @GetMapping("/reverse")
    ReverseGeocodingApiResponse reverseGeocodingRequest(@RequestParam("key") String apiKey, @RequestParam("lat") String latitude,
                                                        @RequestParam("lon") String longitude,
                                                        @RequestParam(value = "format", defaultValue = "json") String format);
    @GetMapping("/search")
    List<ForwardGeocodingApiResponse> geocodingRequest(@RequestParam("key") String apiKey, @RequestParam("q") String searchQueue,
                                                       @RequestParam(value = "format", defaultValue = "json") String format);
}
