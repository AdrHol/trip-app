package org.holubecky.adapters.out.persistance.repository.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.time.LocalDateTime;

@Document(indexName = "products")
@NoArgsConstructor
@Getter
@Setter
public class ProductEntity {
    @Id
    private String id;
    @Field(type = FieldType.Text, analyzer = "standard")
    private String title;
    @Field(type = FieldType.Search_As_You_Type)
    private String title_as_type;
    @Field(type = FieldType.Text, analyzer = "standard")
    private String description;
    @CreatedDate
    @Field(type = FieldType.Date)
    private LocalDateTime createdAt;
    private String createdBy;
}
