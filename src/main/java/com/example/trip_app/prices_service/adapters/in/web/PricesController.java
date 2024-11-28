package com.example.trip_app.prices_service.adapters.in.web;

import com.example.trip_app.prices_service.application.domain.entities.Price;
import com.example.trip_app.prices_service.application.ports.in.web.GetPricesUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/prices")
@RequiredArgsConstructor
public class PricesController {

    private final GetPricesUseCase getPricesUseCase;

    @GetMapping()
    public ResponseEntity<List<Price>> getRecentlySearchedPrices() {
        Price price = new Price();
        price.setProduct("recently works !");
        return ResponseEntity.ok().body(List.of(price));
    }

    @GetMapping(params = {"long", "lat"})
    public ResponseEntity<List<Price>> getPricesByCoordinates(@RequestParam("long") String longitude, @RequestParam("lat") String latitude) {
        Price price = new Price();
        price.setProduct(longitude);
        price.setLocation(latitude);
        return ResponseEntity.ok().body(List.of(price));
    }

    @GetMapping(params = "city")
    public ResponseEntity<List<Price>> getPricesByCity(@RequestParam("city") String city) {
        Price price = new Price();
        price.setProduct(city);
        return ResponseEntity.ok().body(List.of(price));
    }

    @GetMapping(params = "country")
    public ResponseEntity<List<Price>> getPricesByCountry(@RequestParam("country") String country) {
        Price price = new Price();
        price.setLocation(country);
        return ResponseEntity.ok().body(List.of(price));
    }
}
