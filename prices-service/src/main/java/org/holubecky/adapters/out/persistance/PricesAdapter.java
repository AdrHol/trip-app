package org.holubecky.adapters.out.persistance;


import lombok.RequiredArgsConstructor;
import org.holubecky.adapters.out.persistance.repositories.PricesRepository;
import org.holubecky.application.domain.entities.Price;
import org.holubecky.application.ports.out.persistance.PricesCreationPort;
import org.holubecky.application.ports.out.persistance.PricesRetrievalPort;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class PricesAdapter implements PricesRetrievalPort, PricesCreationPort {

    private final PricesRepository pricesRepository;


    @Override
    public List<Price> getAllPrices() {
        System.out.println("Hello");
        return pricesRepository.findAll();
    }

    @Override
    public List<Price> getPricesByCords(String longitude, String latitude) {
        return pricesRepository.getAllPricesByCoordinates(longitude,latitude);
    }

    @Override
    public List<Price> getPricesByLocation(String country, String city) {
        return pricesRepository.getAllPricesByCityAndCountry(country, city);
    }

    @Override
    public Price createPrice(Price price) {
        return pricesRepository.save(price);
    }
}
