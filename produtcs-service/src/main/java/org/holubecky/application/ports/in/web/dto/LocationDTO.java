package org.holubecky.application.ports.in.web.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class LocationDTO {
    private String city;
    private String country;
    private Double lon;
    private Double lat;
}
