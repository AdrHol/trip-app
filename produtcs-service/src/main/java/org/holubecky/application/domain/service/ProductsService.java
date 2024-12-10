package org.holubecky.application.domain.service;

import lombok.RequiredArgsConstructor;
import org.holubecky.adapters.out.persistance.repository.ProductEntity;
import org.holubecky.application.domain.model.Location;
import org.holubecky.application.domain.model.Product;
import org.holubecky.application.ports.in.web.CreateProductUseCase;
import org.holubecky.application.ports.in.web.dto.ProductCreationRequest;
import org.holubecky.application.ports.out.persistance.CreateProductPort;
import org.holubecky.application.ports.out.web.GeoCodingPort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductsService implements CreateProductUseCase {

    private final CreateProductPort createProductPort;
    private final GeoCodingPort geoCodingPort;


    @Override
    public List<ProductEntity> fetchSimilarProducts(ProductCreationRequest productCreationRequest) {

        if(!productCreationRequest.hasCoordinatesFilled()){
            Location fetchedLocationEntity = geoCodingPort.getCoordinatesByLocation(productCreationRequest.getCity(), productCreationRequest.getCountry());
        }
        Product product = new Product();

        return createProductPort.fetchSimilarProducts(product);
    }

    @Override
    public ProductEntity addNewProduct() {
        return null;
    }

}
