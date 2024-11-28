package com.example.trip_app.prices_service.application.ports.in.web;

import com.example.trip_app.prices_service.application.domain.entities.Price;
import com.example.trip_app.prices_service.application.domain.entities.Product;

import java.util.List;

public interface GetPricesUseCase {
    List<Price> getRecentlySearchedPrices();
    List<Price> getPricesByCoordinates(String longitude, String latitude);
    List<Price> getPricesByCityAndCountry(String city, String country);

}
