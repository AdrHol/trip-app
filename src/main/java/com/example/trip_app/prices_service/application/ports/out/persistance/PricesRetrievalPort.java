package com.example.trip_app.prices_service.application.ports.out.persistance;

import com.example.trip_app.prices_service.application.domain.entities.Price;

import java.util.List;

public interface PricesRetrievalPort {
    List<Price> getAllPrices();
    List<Price> getPricesByCords(String longitude, String latitude);
    List<Price> getPricesByLocation(String country, String city);
}
