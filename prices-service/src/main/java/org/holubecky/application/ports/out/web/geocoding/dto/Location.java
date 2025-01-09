package org.holubecky.application.ports.out.web.geocoding.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Location {
    private String city;
    private String country;
    private Double lon;
    private Double lat;
}
