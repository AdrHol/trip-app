package com.example.trip_app.prices_service.application.ports.in.web;

import com.example.trip_app.prices_service.application.domain.entities.Price;

public interface NewPriceUseCase {

    Price createPriceUseCase(Price price);
}
