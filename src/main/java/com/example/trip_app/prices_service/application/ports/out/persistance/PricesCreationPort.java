package com.example.trip_app.prices_service.application.ports.out.persistance;

import com.example.trip_app.prices_service.application.domain.entities.Price;

public interface PricesCreationPort {
    Price createPrice(Price price);
}