package org.holubecky.application.domain.entities.mappers;

import org.holubecky.adapters.out.persistance.repositories.CostEntity;
import org.holubecky.adapters.out.persistance.repositories.LocationEntity;
import org.holubecky.adapters.out.persistance.repositories.PriceEntity;
import org.holubecky.adapters.out.web.services.product.dto.ProductDTO;
import org.holubecky.application.domain.entities.Cost;
import org.holubecky.application.domain.entities.Price;

import org.holubecky.application.domain.exceptions.LocationNotFoundException;
import org.holubecky.application.ports.in.web.commands.CreatePriceCommand;
import org.holubecky.application.ports.out.web.dto.LocationDTO;
import org.holubecky.application.ports.out.web.dto.PriceDTO;
import org.holubecky.application.ports.out.web.geocoding.dto.Location;
import org.springframework.data.elasticsearch.core.geo.GeoPoint;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

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
        return new PriceDTO(price.getUserId(), price.getProductId(), locationDTO, price.getCost());
    }
    public Price priceEntityToDomainModel(PriceEntity priceEntity){
        Price price = new Price();
        price.setId(priceEntity.getId());
        price.setUserId(priceEntity.getUserId());
        price.setProductId(priceEntity.getProductId());
        price.setCost(
                Cost.builder().price(priceEntity.getCost().getPrice()).currency(priceEntity.getCost().getCurrency()).build());
        price.setCity(priceEntity.getLocationEntity().getCity());
        price.setCountry(priceEntity.getLocationEntity().getCountry());
        price.setLatitude(priceEntity.getLocationEntity().getCoordinates().getLat());
        price.setLongitude(priceEntity.getLocationEntity().getCoordinates().getLon());

        return price;
    }
    public PriceEntity priceDomainModelToEntity(Price price) {
        if(price.hasNoLocationData()){
            throw new LocationNotFoundException();
        }
        PriceEntity priceEntity = new PriceEntity();
        priceEntity.setId(price.getId());
        priceEntity.setUserId(price.getUserId());
        priceEntity.setProductId(price.getProductId());
        priceEntity.setCost(
                CostEntity.builder().price(price.getCost().getPrice()).currency(price.getCost().getCurrency()).build());
        priceEntity.setLocationEntity(LocationEntity.builder().coordinates(new GeoPoint(price.getLatitude(),
                price.getLongitude())).city(price.getCity()).country(price.getCountry()).build());

        return priceEntity;
    }

    public void mapProductResponseToPrice(ProductDTO productDTO, Price price){
        price.setPostedAt(LocalDateTime.now());
        price.setCity(productDTO.location().city());
        price.setCountry(productDTO.location().country());
        price.setLongitude(productDTO.location().lon());
        price.setLatitude(productDTO.location().lat());
    }
    public void mapLocation(Location location, Price price){
        String city = location.getCity() != null ? location.getCity() : "NO_CITY";
        price.setCity(city);
        price.setCountry(location.getCountry());
        price.setLatitude(location.getLat());
        price.setLongitude(location.getLon());
    }
}
