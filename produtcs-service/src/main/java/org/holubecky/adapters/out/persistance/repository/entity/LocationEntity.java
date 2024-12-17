package org.holubecky.adapters.out.persistance.repository.entity;

import lombok.*;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;
import org.springframework.data.elasticsearch.core.geo.GeoPoint;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LocationEntity {
    @Field(type = FieldType.Keyword)
    private String city;
    @Field(type = FieldType.Keyword)
    private String country;
    @Field
    private GeoPoint coordinates;
}
