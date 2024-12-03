package org.holubecky.adapters.in.web;

import lombok.RequiredArgsConstructor;
import org.holubecky.application.commons.validators.PriceValidator;
import org.holubecky.application.domain.entities.Price;
import org.holubecky.application.domain.exceptions.InvalidCommandException;
import org.holubecky.application.ports.in.web.GetPricesUseCase;
import org.holubecky.application.ports.in.web.NewPriceUseCase;
import org.holubecky.application.ports.in.web.commands.CreatePriceCommand;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/prices")
@RequiredArgsConstructor
public class PricesController {

    private final GetPricesUseCase getPricesUseCase;
    private final NewPriceUseCase newPriceUseCase;

    @GetMapping()
    public ResponseEntity<List<Price>> getRecentlySearchedPrices() {
        return ResponseEntity.ok().body(getPricesUseCase.getRecentlySearchedPrices());
    }

    @GetMapping(params = {"long", "lat"})
    public ResponseEntity<List<Price>> getPricesByCoordinates(@RequestParam("long") String longitude,
                                                              @RequestParam("lat") String latitude) {
        return ResponseEntity.ok().body(getPricesUseCase.getPricesByCoordinates(longitude, latitude));
    }

    @GetMapping(params = {"city", "country"})
    public ResponseEntity<List<Price>> getPricesByCityAndCountry(@RequestParam("city") String city,
                                                                 @RequestParam("country") String country){
        return ResponseEntity.ok().body(getPricesUseCase.getPricesByCityAndCountry(city, country));
    }

    @PostMapping
    public ResponseEntity<Price> postPrice(@RequestBody CreatePriceCommand createPriceCommand){
        if(!PriceValidator.canDeriveLocation(createPriceCommand)){
            throw new InvalidCommandException();
        }

        return ResponseEntity.ok(newPriceUseCase.createPriceUseCase(createPriceCommand));
    }
}
