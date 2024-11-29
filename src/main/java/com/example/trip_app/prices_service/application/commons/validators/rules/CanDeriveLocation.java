package com.example.trip_app.prices_service.application.commons.validators.rules;

import com.example.trip_app.prices_service.application.ports.in.web.commands.CreatePriceCommand;

import java.util.function.Predicate;

public class CanDeriveLocation implements Predicate<CreatePriceCommand> {
    @Override
    public boolean test(CreatePriceCommand o) {
        return (o.latitude() != null && !o.latitude().isEmpty()) || (o.city() != null && !o.city().isEmpty());
    }
}
