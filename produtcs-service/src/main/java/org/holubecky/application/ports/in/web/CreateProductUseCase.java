package org.holubecky.application.ports.in.web;

import org.holubecky.application.domain.model.Product;
import org.holubecky.application.ports.in.web.dto.ProductCreationRequest;
import org.holubecky.application.ports.in.web.dto.ProductRequestResponse;

import java.util.List;

public interface CreateProductUseCase {

    ProductRequestResponse fetchSimilarProducts(ProductCreationRequest productCreationRequest);
    ProductRequestResponse addNewProduct(ProductCreationRequest productCreationRequest);
}
