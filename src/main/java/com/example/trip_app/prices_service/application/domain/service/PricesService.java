package com.example.trip_app.prices_service.application.domain.service;

import com.example.trip_app.prices_service.application.domain.entities.Price;
import com.example.trip_app.prices_service.application.ports.in.web.GetPricesUseCase;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PricesService implements GetPricesUseCase {
    @Override
    public List<Price> getRecentlySearchedPrices() {
        return null;
    }

    @Override
    public List<Price> getPricesByCoordinates(String longitude, String latitude) {
        return null;
    }

    @Override
    public List<Price> getPricesByCity(String city) {
        return null;
    }

    @Override
    public List<Price> getPricesByCountry(String country) {
        return null;
    }
}
