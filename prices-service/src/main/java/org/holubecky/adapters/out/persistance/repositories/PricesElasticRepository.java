package org.holubecky.adapters.out.persistance.repositories;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface PricesElasticRepository extends ElasticsearchRepository<PriceEntity, String> {

}
