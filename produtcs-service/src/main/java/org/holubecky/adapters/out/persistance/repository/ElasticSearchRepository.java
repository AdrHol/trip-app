package org.holubecky.adapters.out.persistance.repository;

import org.holubecky.application.domain.entity.ProductEntity;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface ElasticSearchRepository extends ElasticsearchRepository<ProductEntity, String> {
}
