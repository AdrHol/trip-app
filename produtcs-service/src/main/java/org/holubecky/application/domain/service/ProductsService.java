package org.holubecky.application.domain.service;

import lombok.RequiredArgsConstructor;
import org.holubecky.application.domain.entity.ProductEntity;
import org.holubecky.application.ports.in.web.CreateProductUseCase;
import org.holubecky.application.ports.in.web.dto.ProductCreationRequest;
import org.holubecky.application.ports.out.persistance.CreateProductPort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductsService implements CreateProductUseCase {

    private final CreateProductPort createProductPort;

    @Override
    public List<ProductEntity> fetchSimilarProducts(ProductCreationRequest productCreationRequest) {
        return createProductPort.fetchSimilarProducts(productCreationRequest);
    }

    @Override
    public ProductEntity addNewProduct() {
        return null;
    }
}
