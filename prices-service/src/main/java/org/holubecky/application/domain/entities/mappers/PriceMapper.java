package org.holubecky.application.domain.entities.mappers;

import org.holubecky.adapters.out.persistance.repositories.PriceEntity;
import org.holubecky.application.domain.entities.Cost;
import org.holubecky.application.domain.entities.Price;

import org.holubecky.application.domain.exceptions.LocationNotFoundException;
import org.holubecky.application.ports.in.web.commands.CreatePriceCommand;
import org.holubecky.application.ports.out.web.dto.LocationDTO;
import org.holubecky.application.ports.out.web.dto.PriceDTO;
import org.springframework.stereotype.Component;

@Component
public class PriceMapper {

    public Price mapCreateCommandToPrice(CreatePriceCommand createPriceCommand){
        Price price = new Price();
        price.setUserId(createPriceCommand.userId());
        price.setProductId(createPriceCommand.productId());
        price.setCost(
                Cost.builder().price(createPriceCommand.price()).currency(createPriceCommand.currency()).build());
        return price;
    }
    public PriceDTO mapPriceToDTO(Price price){
        LocationDTO locationDTO = new LocationDTO(price.getCity(), price.getCountry(), price.getLongitude(), price.getLatitude());
        PriceDTO priceDTO = new PriceDTO(price.getUserId(), price.getProductId(), locationDTO, price.getCost());
        return priceDTO;
    }
    public Price priceEntityToDomainModel(PriceEntity priceEntity){
        Price price = new Price();
        price.setId(priceEntity.getId());
        price.setUserId(priceEntity.getUserId());
        price.setProductId(priceEntity.getProductId());
        price.setCost(
                Cost.builder().price(priceEntity.getCost().getPrice()).currency(priceEntity.getCost().getCurrency()).build());
        price.setCity(priceEntity.getCity());
        price.setCountry(priceEntity.getCountry());
        price.setLatitude(priceEntity.getLatitude());
        price.setLongitude(priceEntity.getLongitude());

        return price;
    }
    public PriceEntity priceDomainModelToEntity(Price price) {
        if(price.hasNoLocationData()){
            throw new LocationNotFoundException();
        }
        PriceEntity priceEntity = new PriceEntity();
        priceEntity.setId(price.getId());
        priceEntity.setUserId(priceEntity.getUserId());
        priceEntity.setProductId(priceEntity.getProductId());
        priceEntity.setCost(
                Cost.builder().price(priceEntity.getCost().getPrice()).currency(priceEntity.getCost().getCurrency()).build());
        priceEntity.setCity(priceEntity.getCity());
        priceEntity.setCountry(priceEntity.getCountry());
        priceEntity.setLatitude(priceEntity.getLatitude());
        priceEntity.setLongitude(priceEntity.getLongitude());

        return priceEntity;
    }
}
