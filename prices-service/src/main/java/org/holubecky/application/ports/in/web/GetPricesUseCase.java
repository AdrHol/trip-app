package org.holubecky.application.ports.in.web;

import org.holubecky.application.domain.entities.Price;
import org.holubecky.application.ports.out.web.dto.PriceDTO;

import java.util.List;

public interface GetPricesUseCase {
    List<PriceDTO> getRecentlySearchedPrices();
    List<PriceDTO> getPricesByCoordinates(Double longitude, Double latitude);
    List<PriceDTO> getPricesByCityAndCountry(String city, String country);
    List<PriceDTO> getPricesByProductId(String productId);

}
