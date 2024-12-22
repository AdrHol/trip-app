package org.holubecky.adapters.out.web.services.product;

import lombok.RequiredArgsConstructor;
import org.holubecky.adapters.out.web.services.product.dto.ProductDTO;
import org.holubecky.adapters.out.web.services.product.dto.ProductServiceResponse;
import org.holubecky.adapters.out.web.services.product.exception.ProductNotFoundException;
import org.holubecky.adapters.out.web.services.product.feign.ProductServiceClient;
import org.holubecky.application.ports.out.web.dto.LocationDTO;
import org.holubecky.application.ports.out.web.services.product.ProductServicePort;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ProductServiceAdapter implements ProductServicePort {
    private final ProductServiceClient client;

    @Override
    public ProductDTO requestProductDetails(String productId) {
        ProductServiceResponse response = client.getProductById(productId);
        if(!response.isResponse()){
            throw new ProductNotFoundException(productId);
        }
        return response.getBody().get(0);
    }

}
