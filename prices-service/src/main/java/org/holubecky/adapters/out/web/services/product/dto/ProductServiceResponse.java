package org.holubecky.adapters.out.web.services.product.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ProductServiceResponse {

    private boolean response;
    private List<ProductDTO> body;

}
