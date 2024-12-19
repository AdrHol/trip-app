package org.holubecky.application.ports.in.web.dto;

import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Builder
@AllArgsConstructor
public class ProductRequestResponse {
    private boolean response;
    private List<ProductDTO> body = new ArrayList<>();
}
