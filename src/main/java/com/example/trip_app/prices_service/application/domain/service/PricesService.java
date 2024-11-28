package com.example.trip_app.prices_service.application.domain.service;

import com.example.trip_app.prices_service.application.domain.entities.Price;
import com.example.trip_app.prices_service.application.domain.entities.Product;
import com.example.trip_app.prices_service.application.ports.in.web.GetPricesUseCase;
import com.example.trip_app.prices_service.application.ports.in.web.NewPriceUseCase;
import com.example.trip_app.prices_service.application.ports.out.PricesCreationPort;
import com.example.trip_app.prices_service.application.ports.out.PricesRetrievalPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PricesService implements GetPricesUseCase, NewPriceUseCase {

    private final PricesRetrievalPort pricesRetrievalPort;
    private final PricesCreationPort pricesCreationPort;

    @Override
    public List<Price> getRecentlySearchedPrices() {
        return pricesRetrievalPort.getAllPrices();
    }

    @Override
    public List<Price> getPricesByCoordinates(String longitude, String latitude) {
        return pricesRetrievalPort.getPricesByCords(longitude, latitude);
    }

    @Override
    public List<Price> getPricesByCityAndCountry(String city, String country) {
        return pricesRetrievalPort.getPricesByLocation(country, city);
    }

    @Override
    public Price createPriceUseCase(Price price) {
        return pricesCreationPort.createPrice(price);
    }
}
