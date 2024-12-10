package org.holubecky.application.domain.model;

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
