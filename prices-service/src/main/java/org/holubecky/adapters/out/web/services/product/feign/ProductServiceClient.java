package org.holubecky.adapters.out.web.services.product.feign;

import org.holubecky.adapters.out.web.services.product.dto.ProductServiceResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "product-service", url = "http://product-service:8081")
public interface ProductServiceClient {
    @GetMapping("/id")
    public ProductServiceResponse getProductById(@RequestParam(name = "id") String id);
}
