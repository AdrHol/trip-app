package org.holubecky.adapters.out.persistance.repository;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;
import org.springframework.data.elasticsearch.annotations.TermVector;

import java.time.LocalDateTime;

@Document(indexName = "products")
@NoArgsConstructor
@Getter
@Setter
public class ProductEntity {
    @Id
    private String id;
    @Field(type = FieldType.Text, termVector = TermVector.with_positions_offsets, analyzer = "standard")
    private String title;
    @Field(type = FieldType.Text, termVector = TermVector.with_positions_offsets, analyzer = "standard")
    private String description;
    @Field(type = FieldType.Date)
    private LocalDateTime createdAt;
    private String createdBy;
    @Field(type = FieldType.Nested)
    private LocationEntity locationEntity;
}
