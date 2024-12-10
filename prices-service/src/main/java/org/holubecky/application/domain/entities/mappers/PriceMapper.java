package org.holubecky.application.domain.entities.mappers;

import org.holubecky.application.domain.entities.Cost;
import org.holubecky.application.domain.entities.Price;

import org.holubecky.application.ports.in.web.commands.CreatePriceCommand;
import org.springframework.stereotype.Component;

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
