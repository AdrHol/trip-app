package org.holubecky.application.ports.out.persistance;

import org.holubecky.application.domain.entity.ProductEntity;

public interface NewProductPort {

    ProductEntity saveProduct(ProductEntity product);
}
