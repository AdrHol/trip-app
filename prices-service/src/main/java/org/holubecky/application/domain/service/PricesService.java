package org.holubecky.application.domain.service;

import org.holubecky.application.domain.entities.Price;
import org.holubecky.application.domain.entities.mappers.PriceMapper;

import lombok.RequiredArgsConstructor;
import org.holubecky.application.ports.in.web.GetPricesUseCase;
import org.holubecky.application.ports.in.web.NewPriceUseCase;
import org.holubecky.application.ports.in.web.commands.CreatePriceCommand;
import org.holubecky.application.ports.out.persistance.PricesCreationPort;
import org.holubecky.application.ports.out.persistance.PricesRetrievalPort;
import org.holubecky.application.ports.out.web.GeoCodingPort;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PricesService implements GetPricesUseCase, NewPriceUseCase {

    private final PricesRetrievalPort pricesRetrievalPort;
    private final PricesCreationPort pricesCreationPort;
    private final GeoCodingPort geoCodingPort;
    private final PriceMapper priceMapper;

    @Override
    public List<Price> getRecentlySearchedPrices() {
        return pricesRetrievalPort.getAllPrices();
    }

    @Override
    public List<Price> getPricesByCoordinates(String longitude, String latitude) {
        return pricesRetrievalPort.getPricesByCords(longitude, latitude);
    }

    @Override
    public List<Price> getPricesByCityAndCountry(String city, String country) {
        return pricesRetrievalPort.getPricesByLocation(country, city);
    }

    @Override
    public Price createPriceUseCase(CreatePriceCommand createPriceCommand) {
        Price price = priceMapper.mapCreateCommandToPrice(createPriceCommand);
        price.setPostedAt(LocalDateTime.now());

        if(price.hasCoordinatesFilled() && !price.hasCityAndCountry()) {
            findLocationByCoordinates(price);
        }

        if(price.hasCityAndCountry() && !price.hasCoordinatesFilled()) {
            findCoordinatesByCityAndCountry(price);
        }
        return pricesCreationPort.createPrice(price);
    }
    private void findLocationByCoordinates(Price price){
        geoCodingPort.getLocationByCoordinates(price.getLocation().getLongitude(), price.getLocation().getLatitude());
    }
    private void findCoordinatesByCityAndCountry(Price price){
        geoCodingPort.getCoordinatesByLocation(price.getLocation().getCity(), price.getLocation().getCountry());
    }
}
