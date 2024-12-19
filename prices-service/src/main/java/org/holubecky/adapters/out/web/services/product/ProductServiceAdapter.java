package org.holubecky.adapters.out.web.services.product;

import org.holubecky.adapters.out.web.services.product.dto.ProductDTO;
import org.holubecky.application.ports.out.web.dto.LocationDTO;
import org.holubecky.application.ports.out.web.services.product.ProductServicePort;
import org.springframework.stereotype.Component;

@Component
public class ProductServiceAdapter implements ProductServicePort {
    @Override
    public ProductDTO requestProductDetails(String productId) {
        ProductDTO dummyProduct = new ProductDTO("asefa", "asefsf", "ffsefa", new LocationDTO("gtrh", "crfs", 54.3, 56.3));
        return dummyProduct;
    }
}
