package org.holubecky.application.ports.out.web.services.product;

import org.holubecky.adapters.out.web.services.product.dto.ProductDTO;

public interface ProductServicePort {
     ProductDTO requestProductDetails(String productId);
}
