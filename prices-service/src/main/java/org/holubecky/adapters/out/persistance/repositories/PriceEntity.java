package org.holubecky.adapters.out.persistance.repositories;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.time.LocalDateTime;


@Document(indexName = "prices")
@Getter @Setter
@NoArgsConstructor
public class PriceEntity {
    @Id
    private String id;
    private String userId;
    @Field(type = FieldType.Keyword)
    private String productId;
    @CreatedDate
    @Field(type = FieldType.Date)
    private LocalDateTime postedAt;
    @Field(type = FieldType.Nested)
    private CostEntity cost;
    @Field(type = FieldType.Nested)
    private LocationEntity locationEntity;
}
