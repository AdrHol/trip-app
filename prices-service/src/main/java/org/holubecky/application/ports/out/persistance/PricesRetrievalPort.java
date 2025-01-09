package org.holubecky.application.ports.out.persistance;

import org.holubecky.application.domain.entities.Price;

import java.util.List;

public interface PricesRetrievalPort {
    List<Price> getAllPrices();
    List<Price> getPricesByCords(Double longitude, Double latitude, String productId);
    List<Price> getPricesByLocation(String country, String city);
    List<Price> getPricesByProductId(String productId);
}
