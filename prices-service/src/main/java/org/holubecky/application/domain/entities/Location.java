package org.holubecky.application.domain.entities;

import jakarta.persistence.Embeddable;
import lombok.*;

@Embeddable
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Location {
    private String longitude;
    private String latitude;
    private String city;
    private String country;
}
