package org.holubecky.application.ports.out.persistance;

import org.holubecky.application.domain.entity.ProductEntity;
import org.holubecky.application.ports.in.web.dto.ProductCreationRequest;

import java.util.List;

public interface CreateProductPort {

    List<ProductEntity> fetchSimilarProducts(ProductCreationRequest request);
    ProductEntity saveProduct(ProductEntity product);
}
