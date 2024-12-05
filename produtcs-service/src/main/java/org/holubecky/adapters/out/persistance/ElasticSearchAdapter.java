package org.holubecky.adapters.out.persistance;

import lombok.RequiredArgsConstructor;
import org.holubecky.application.domain.entity.ProductEntity;
import org.holubecky.application.ports.out.persistance.NewProductPort;
import org.holubecky.application.ports.out.persistance.RetrieveProductPort;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.IndexInformation;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class ElasticSearchAdapter implements NewProductPort, RetrieveProductPort {

    private final ElasticsearchOperations elasticsearchOperations;

    @Override
    public ProductEntity saveProduct(ProductEntity product) {
        ProductEntity result = elasticsearchOperations.save(product);
        return result;
    }


    @Override
    public List<IndexInformation> getProducts() {
        return elasticsearchOperations.indexOps(ProductEntity.class).getInformation();
    }
}
