package com.example.trip_app.prices_service.application.ports.in.web;

import com.example.trip_app.prices_service.application.domain.entities.Price;
import com.example.trip_app.prices_service.application.ports.in.web.commands.CreatePriceCommand;

public interface NewPriceUseCase {

    Price createPriceUseCase(CreatePriceCommand createPriceCommand);
}
