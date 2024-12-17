package org.holubecky.application.ports.in.web;

import org.holubecky.application.domain.model.Product;
import org.holubecky.application.ports.in.web.dto.ProductRequestResponse;

import java.util.Optional;

public interface FetchProductUseCase {
    ProductRequestResponse getProductById(String id);
    ProductRequestResponse getProductByTitleAndOrDescription(Optional<String> title, Optional<String> description);
}
