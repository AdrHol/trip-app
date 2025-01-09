package org.holubecky.adapters.out.persistance.repositories;

import lombok.*;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CostEntity {
    @Field(type = FieldType.Keyword)
    private String currency;
    private long price;
}
