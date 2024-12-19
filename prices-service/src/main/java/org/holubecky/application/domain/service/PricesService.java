package org.holubecky.application.domain.service;

import org.holubecky.adapters.out.web.services.product.dto.ProductDTO;
import org.holubecky.application.domain.entities.Price;
import org.holubecky.application.domain.entities.mappers.PriceMapper;

import lombok.RequiredArgsConstructor;
import org.holubecky.application.domain.exceptions.LocationNotFoundException;
import org.holubecky.application.ports.in.web.GetPricesUseCase;
import org.holubecky.application.ports.in.web.NewPriceUseCase;
import org.holubecky.application.ports.in.web.commands.CreatePriceCommand;
import org.holubecky.application.ports.out.persistance.PricesCreationPort;
import org.holubecky.application.ports.out.persistance.PricesRetrievalPort;
import org.holubecky.application.ports.out.web.dto.PriceDTO;
import org.holubecky.application.ports.out.web.services.product.ProductServicePort;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PricesService implements GetPricesUseCase, NewPriceUseCase {

    private final PricesRetrievalPort pricesRetrievalPort;
    private final PricesCreationPort pricesCreationPort;
    private final ProductServicePort productServicePort;
    private final PriceMapper priceMapper;

    @Override
    public List<PriceDTO> getRecentlySearchedPrices() {
        return pricesRetrievalPort.getAllPrices().stream().map(priceMapper::mapPriceToDTO).toList();
    }

    @Override
    public List<PriceDTO> getPricesByCoordinates(Double longitude, Double latitude) {
        return pricesRetrievalPort.getPricesByCords(longitude, latitude).stream().map(priceMapper::mapPriceToDTO).toList();
    }

    @Override
    public List<PriceDTO> getPricesByCityAndCountry(String city, String country) {
        return pricesRetrievalPort.getPricesByLocation(country, city).stream().map(priceMapper::mapPriceToDTO).toList();
    }

    @Override
    public List<PriceDTO> getPricesByProductId(String productId) {
        return pricesRetrievalPort.getPricesByProductId(productId).stream().map(priceMapper::mapPriceToDTO).toList();
    }

    @Override
    public PriceDTO createPriceUseCase(CreatePriceCommand createPriceCommand) {
        Price price = priceMapper.mapCreateCommandToPrice(createPriceCommand);

        ProductDTO productServiceResponse = productServicePort.requestProductDetails(createPriceCommand.productId());
        priceMapper.mapProductResponseToPrice(productServiceResponse, price);

        return priceMapper.mapPriceToDTO(pricesCreationPort.createPrice(price));
    }

}
