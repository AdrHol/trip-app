package org.holubecky.adapters.out.persistance.repository;

import org.holubecky.adapters.out.persistance.repository.entity.ProductEntity;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface ElasticSearchRepository extends ElasticsearchRepository<ProductEntity, String> {
}
