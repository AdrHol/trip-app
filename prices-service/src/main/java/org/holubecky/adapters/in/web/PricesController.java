package org.holubecky.adapters.in.web;

import lombok.RequiredArgsConstructor;
import org.holubecky.application.domain.entities.Price;
import org.holubecky.application.ports.in.web.GetPricesUseCase;
import org.holubecky.application.ports.in.web.NewPriceUseCase;
import org.holubecky.application.ports.in.web.commands.CreatePriceCommand;
import org.holubecky.application.ports.out.web.dto.PriceDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1")
@RequiredArgsConstructor
public class PricesController {

    private final GetPricesUseCase getPricesUseCase;
    private final NewPriceUseCase newPriceUseCase;

    @GetMapping()
    public ResponseEntity<List<PriceDTO>> getRecentlySearchedPrices() {
        return ResponseEntity.ok().body(getPricesUseCase.getRecentlySearchedPrices());
    }

    @GetMapping("/prod")
    public ResponseEntity<List<PriceDTO>> getPricesByIdProduct(@RequestParam("id") String productId){
        return ResponseEntity.ok(getPricesUseCase.getPricesByProductId(productId));
    }

    @GetMapping(params = {"long", "lat"})
    public ResponseEntity<List<PriceDTO>> getPricesByCoordinates(@RequestParam("long") String longitude,
                                                                 @RequestParam("lat") String latitude,
                                                                 @RequestParam(value = "prodId", required = false) String productId) {
        String product = productId == null ? "" : productId;
        return ResponseEntity.ok().body(getPricesUseCase.getPricesByCoordinates(Double.parseDouble(longitude),
                Double.parseDouble(latitude),
                product));
    }

    @GetMapping(params = {"city", "country"})
    public ResponseEntity<List<PriceDTO>> getPricesByCityAndCountry(@RequestParam("city") String city,
                                                                    @RequestParam("country") String country,
                                                                    @RequestParam(value = "prodId", required = false) String productId){
        String product = productId == null ? "" : productId;
        return ResponseEntity.ok().body(getPricesUseCase.getPricesByCityAndCountry(city, country));
    }

    @PostMapping("/add")
    public ResponseEntity<PriceDTO> postPrice(@RequestBody CreatePriceCommand createPriceCommand){
        return ResponseEntity.ok(newPriceUseCase.createPriceUseCase(createPriceCommand));
    }
}
