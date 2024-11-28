package com.example.trip_app.prices_service.application.ports.in.web;

import com.example.trip_app.prices_service.application.domain.entities.Price;

import java.util.List;

public interface GetPricesUseCase {
    List<Price> getRecentlySearchedPrices();
    List<Price> getPricesByCoordinates(String longitude, String latitude);
    List<Price> getPricesByCity(String city);
    List<Price> getPricesByCountry(String country);
}
