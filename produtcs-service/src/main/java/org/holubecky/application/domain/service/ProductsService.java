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
    public List<Product> fetchSimilarProducts(ProductCreationRequest productCreationRequest) {
        Product product = prepareProduct(productCreationRequest);
        checkLocationData(product, productCreationRequest);
        return createProductPort.fetchSimilarProducts(product);
    }

    @Override
    public Product addNewProduct() {
        return null;
    }

    private Product prepareProduct(ProductCreationRequest productCreationRequest){
        return Product.builder()
                .description(productCreationRequest.getDescription())
                .title(productCreationRequest.getTitle())
                .build();
    }
    private void checkLocationData(Product domainObject, ProductCreationRequest productCreationRequest){
        if(productCreationRequest.hasCoordinatesFilled() && productCreationRequest.hasCityAndCountry()){
            return;
        }

        Location newLocation = productCreationRequest.hasCoordinatesFilled() ?
                geoCodingPort.getLocationByCoordinates(productCreationRequest.getLon(), productCreationRequest.getLat())
                : geoCodingPort.getCoordinatesByLocation(productCreationRequest.getCity(), productCreationRequest.getCountry());

        domainObject.setLocation(newLocation);
    }
}
