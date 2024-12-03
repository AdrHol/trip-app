package org.holubecky.application.domain.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "prices")
@NoArgsConstructor
@Getter
@Setter
public class Price {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String userId;
    private String product;
    private LocalDateTime postedAt;
    @Embedded
    private Cost cost;
    @Embedded
    private Location location;

    public boolean hasCityAndCountry(){
        boolean isCityFilled = this.getLocation().getCity() != null && !this.getLocation().getCity().isEmpty();
        boolean isCountryFilled = this.getLocation().getCountry() != null && !this.getLocation().getCountry().isEmpty();
        return isCityFilled && isCountryFilled;
    }
    public boolean hasCoordinatesFilled(){
        boolean isLongitudePresent = this.getLocation().getLongitude() != null && !this.getLocation().getLongitude().isEmpty();
        boolean isLatitudePresent = this.getLocation().getLatitude() != null && !this.getLocation().getLatitude().isEmpty();
        return isLatitudePresent && isLongitudePresent;
    }
}
