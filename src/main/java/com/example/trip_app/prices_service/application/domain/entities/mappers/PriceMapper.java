package com.example.trip_app.prices_service.application.domain.entities.mappers;

import com.example.trip_app.prices_service.application.domain.entities.Cost;
import com.example.trip_app.prices_service.application.domain.entities.Location;
import com.example.trip_app.prices_service.application.domain.entities.Price;
import com.example.trip_app.prices_service.application.ports.in.web.commands.CreatePriceCommand;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class PriceMapper {

    public Price mapCreateCommandToPrice(CreatePriceCommand createPriceCommand){
        Price price = new Price();
        price.setUserId(createPriceCommand.userId());
        price.setProduct(createPriceCommand.product());
        price.setCost(
                Cost.builder().price(createPriceCommand.price()).currency(createPriceCommand.currency()).build());
        price.setLocation(Location.builder()
                .city(createPriceCommand.city())
                .country(createPriceCommand.country())
                .latitude(createPriceCommand.latitude())
                .longitude(createPriceCommand.longitude()).build());

        return price;
    }
}
