package org.holubecky.application.ports.in.web;

import org.holubecky.adapters.out.persistance.repository.ProductEntity;
import org.holubecky.application.ports.in.web.dto.ProductCreationRequest;

import java.util.List;

public interface CreateProductUseCase {

    List<ProductEntity> fetchSimilarProducts(ProductCreationRequest productCreationRequest);
    ProductEntity addNewProduct();
}
