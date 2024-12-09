package org.holubecky.application.ports.out.persistance;

import org.holubecky.adapters.out.persistance.repository.ProductEntity;
import org.holubecky.application.domain.model.Product;

import java.util.List;

public interface CreateProductPort {

    List<Product> fetchSimilarProducts(Product request);
    Product saveProduct(Product product);
}
