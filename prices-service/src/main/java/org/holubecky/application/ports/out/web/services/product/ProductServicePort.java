package org.holubecky.application.ports.out.web.services.product;

import org.holubecky.adapters.out.web.services.product.dto.ProductDTO;
import org.holubecky.application.ports.out.web.dto.PriceDTO;

public interface ProductServicePort {
     ProductDTO requestProductDetails(String productId);
}
