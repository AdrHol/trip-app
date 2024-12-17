package org.holubecky.application.ports.in.web;

import org.holubecky.application.domain.model.Product;
import org.holubecky.application.ports.in.web.dto.ProductCreationRequest;

import java.util.List;

public interface CreateProductUseCase {

    List<Product> fetchSimilarProducts(ProductCreationRequest productCreationRequest);
    Product addNewProduct();
}
