package org.holubecky.application.ports.in.web;

import org.holubecky.application.domain.entities.Price;

import java.util.List;

public interface GetPricesUseCase {
    List<Price> getRecentlySearchedPrices();
    List<Price> getPricesByCoordinates(String longitude, String latitude);
    List<Price> getPricesByCityAndCountry(String city, String country);

}
