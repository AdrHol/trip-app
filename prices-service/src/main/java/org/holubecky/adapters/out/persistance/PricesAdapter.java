package org.holubecky.adapters.out.persistance;


import lombok.RequiredArgsConstructor;
import org.holubecky.adapters.out.persistance.repositories.PriceEntity;
import org.holubecky.adapters.out.persistance.repositories.PricesRepository;
import org.holubecky.application.domain.entities.Price;
import org.holubecky.application.domain.entities.mappers.PriceMapper;
import org.holubecky.application.ports.out.persistance.PricesCreationPort;
import org.holubecky.application.ports.out.persistance.PricesRetrievalPort;
import org.holubecky.application.ports.out.web.services.product.ProductServicePort;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class PricesAdapter implements PricesRetrievalPort, PricesCreationPort {

    private final PricesRepository pricesRepository;
    private final PriceMapper priceMapper;
    private final ProductServicePort productServicePort;


    @Override
    public List<Price> getAllPrices() {
        System.out.println("Hello");
        return pricesRepository.findAll().stream().map(priceMapper::priceEntityToDomainModel).toList();
    }

    @Override
    public List<Price> getPricesByCords(String longitude, String latitude) {
        return pricesRepository.getAllPricesByCoordinates(longitude,latitude).stream().map(priceMapper::priceEntityToDomainModel).toList();
    }

    @Override
    public List<Price> getPricesByLocation(String country, String city) {
        return pricesRepository.getAllPricesByCityAndCountry(country, city).stream().map(priceMapper::priceEntityToDomainModel).toList();
    }

    @Override
    public Price createPrice(Price price) {
        PriceEntity priceEntity = priceMapper.priceDomainModelToEntity(price);
        return priceMapper.priceEntityToDomainModel(priceEntity);
    }
}
