package com.example.trip_app.prices_service.adapters.out.persistance;

import com.example.trip_app.prices_service.adapters.out.persistance.repositories.PricesRepository;
import com.example.trip_app.prices_service.application.domain.entities.Price;
import com.example.trip_app.prices_service.application.ports.out.PricesCreationPort;
import com.example.trip_app.prices_service.application.ports.out.PricesRetrievalPort;
import lombok.RequiredArgsConstructor;
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
