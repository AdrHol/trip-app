package org.holubecky.adapters.out.persistance.repositories;

import lombok.*;
import org.springframework.data.elasticsearch.annotations.Alias;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;
import org.springframework.data.elasticsearch.core.geo.GeoPoint;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LocationEntity {
    @Field(type = FieldType.Keyword)
    private String city;
    @Field(type = FieldType.Keyword)
    private String country;
    @Field
    private GeoPoint coordinates;
}
