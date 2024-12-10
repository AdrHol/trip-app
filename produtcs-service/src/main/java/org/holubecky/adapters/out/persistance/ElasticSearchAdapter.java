package org.holubecky.adapters.out.persistance;

import co.elastic.clients.elasticsearch._types.query_dsl.Like;
import co.elastic.clients.elasticsearch._types.query_dsl.MoreLikeThisQuery;
import lombok.RequiredArgsConstructor;
import org.holubecky.adapters.out.persistance.repository.LocationEntity;
import org.holubecky.application.domain.model.Product;
import org.holubecky.adapters.out.persistance.repository.ProductEntity;
import org.holubecky.application.ports.out.persistance.CreateProductPort;
import org.holubecky.application.ports.out.persistance.RetrieveProductPort;
import org.springframework.data.elasticsearch.client.elc.NativeQuery;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.IndexInformation;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.data.elasticsearch.core.query.Query;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class ElasticSearchAdapter implements CreateProductPort, RetrieveProductPort {

    private final ElasticsearchOperations elasticsearchOperations;

    @Override
    public List<ProductEntity> fetchSimilarProducts(Product queryEntity) {
        MoreLikeThisQuery query = MoreLikeThisQuery.of(
                e -> e.fields("title", "description")
                        .like(new Like.Builder().text(queryEntity.getTitle()).build(), new Like.Builder().text(queryEntity.getDescription()).build())
                        .minTermFreq(1)
                        .maxQueryTerms(12)
                        .analyzer("standard")
        );
        Query nativeQuery = NativeQuery.builder()
                .withQuery(query._toQuery()).build();

        SearchHits<ProductEntity> result = elasticsearchOperations.search(nativeQuery, ProductEntity.class);

        return null;
    }

    @Override
    public ProductEntity saveProduct(Product product) {

        return elasticsearchOperations.save(product);
    }


    @Override
    public List<IndexInformation> getProducts() {
        return elasticsearchOperations.indexOps(ProductEntity.class).getInformation();
    }

    private ProductEntity mapDomainToEntity(Product domainObject){
        ProductEntity productEntity = new ProductEntity();
        productEntity.setTitle(domainObject.getTitle());
        productEntity.setDescription(domainObject.getDescription());
        new LocationEntity();
        productEntity.setLocationEntity(
                LocationEntity.builder().city(domainObject.getLocationEntity().getCity())
                .country(domainObject.getLocationEntity().getCountry())
                .coordinates()
                .build());
        return new ProductEntity();
    }

}
