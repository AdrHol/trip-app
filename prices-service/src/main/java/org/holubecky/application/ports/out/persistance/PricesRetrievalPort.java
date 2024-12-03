package org.holubecky.application.ports.out.persistance;

import org.holubecky.application.domain.entities.Price;

import java.util.List;

public interface PricesRetrievalPort {
    List<Price> getAllPrices();
    List<Price> getPricesByCords(String longitude, String latitude);
    List<Price> getPricesByLocation(String country, String city);
}
