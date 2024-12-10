package org.holubecky.application.ports.in.web.dto;

import ch.qos.logback.core.util.StringUtil;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductCreationRequest {
    private String title;
    private String description;
    private String city;
    private String country;
    private String lon;
    private String lat;

    public boolean hasCityAndCountry(){
        return StringUtil.notNullNorEmpty(city) && StringUtil.notNullNorEmpty(country);
    }
    public boolean hasCoordinatesFilled(){
        return StringUtil.notNullNorEmpty(lon) && StringUtil.notNullNorEmpty(lat);
    }
}
