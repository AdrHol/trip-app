package org.holubecky.application.ports.in.web.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class ProductDTO {
    private String id;
    private String title;
    private String description;
    private LocationDTO locationDTO;
}
