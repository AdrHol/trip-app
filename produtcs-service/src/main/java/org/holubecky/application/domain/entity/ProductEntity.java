package org.holubecky.application.domain.entity;

import co.elastic.clients.elasticsearch._types.analysis.Analyzer;
import co.elastic.clients.elasticsearch._types.analysis.AnalyzerVariant;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

@Document(indexName = "products")
@NoArgsConstructor
@Getter
@Setter
public class ProductEntity {
    @Id
    private String id;
    @Field(type = FieldType.Text)
    private String title;
    @Field(type = FieldType.Text)
    private String description;

}
