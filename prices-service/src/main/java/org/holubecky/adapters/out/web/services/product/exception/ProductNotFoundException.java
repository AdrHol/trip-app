package org.holubecky.adapters.out.web.services.product.exception;

import org.holubecky.adapters.out.web.services.product.dto.ProductDTO;

public class ProductNotFoundException extends RuntimeException{
    public ProductNotFoundException(String id){
        super(String.format("Product with id:%s not found", id));
    }
}
