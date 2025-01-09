package org.holubecky.application.domain.entities;

import ch.qos.logback.core.util.StringUtil;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;


@NoArgsConstructor
@Getter
@Setter
public class Price {

    private String id;
    private String userId;
    private String productId;
    private LocalDateTime postedAt;
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
