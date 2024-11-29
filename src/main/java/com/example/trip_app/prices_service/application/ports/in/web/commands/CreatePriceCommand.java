package com.example.trip_app.prices_service.application.ports.in.web.commands;

import com.example.trip_app.prices_service.application.domain.entities.Cost;

public record CreatePriceCommand(String userId, String product, String currency, long price, String city, String country,
                                 String longitude, String latitude) {
}
