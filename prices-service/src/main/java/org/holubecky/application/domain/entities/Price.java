package org.holubecky.application.domain.entities;

import ch.qos.logback.core.util.StringUtil;
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
    private String productId;
    private LocalDateTime postedAt;
    @Embedded
    private Cost cost;
    private String city;
    private String country;
    private Double latitude;
    private Double longitude;

    public boolean hasNoLocationData(){
        return StringUtil.isNullOrEmpty(this.city) && StringUtil.isNullOrEmpty(this.country) && this.latitude.describeConstable().isEmpty()
                && this.longitude.describeConstable().isEmpty();
    }
}
