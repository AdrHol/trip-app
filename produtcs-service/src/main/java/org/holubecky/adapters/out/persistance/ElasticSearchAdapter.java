package org.holubecky.adapters.out.persistance;

import co.elastic.clients.elasticsearch._types.query_dsl.BoolQuery;
import co.elastic.clients.elasticsearch._types.query_dsl.GeoDistanceQuery;
import co.elastic.clients.elasticsearch._types.query_dsl.MoreLikeThisQuery;
import lombok.RequiredArgsConstructor;
import org.holubecky.adapters.out.persistance.repository.query.QueriesBuilder;
import org.holubecky.application.domain.model.Product;
import org.holubecky.adapters.out.persistance.repository.entity.ProductEntity;
import org.holubecky.application.ports.out.persistance.CreateProductPort;
import org.holubecky.application.ports.out.persistance.RetrieveProductPort;
import org.holubecky.configuration.mappers.ProductMapper;
import org.springframework.data.elasticsearch.client.elc.NativeQuery;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.IndexInformation;
import org.springframework.data.elasticsearch.core.SearchHit;
import org.springframework.data.elasticsearch.core.query.Query;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class ElasticSearchAdapter implements CreateProductPort, RetrieveProductPort {

    private final ElasticsearchOperations elasticsearchOperations;
    private final QueriesBuilder queriesBuilder;
    private final ProductMapper mapper;
    @Override
    public List<Product> fetchSimilarProducts(Product product) {
        MoreLikeThisQuery mlt = queriesBuilder.fullTextQuery(product.getTitle(), product.getDescription());
        GeoDistanceQuery geoQuery = queriesBuilder.findInArea(product.getLocation().getLon(),
                                                            product.getLocation().getLat(),
                                                            "2km");

        BoolQuery andQuery = BoolQuery.of(q -> q.must(mlt._toQuery()).must(geoQuery._toQuery()));
        Query nativeQuery = NativeQuery.builder().withQuery(andQuery._toQuery()).build();

        return elasticsearchOperations.search(nativeQuery, ProductEntity.class).stream()
                .map(SearchHit::getContent)
                .map(mapper::mapEntityToDomain)
                .toList();
    }

    @Override
    public Product saveProduct(Product product) {
        ProductEntity productEntity = elasticsearchOperations.save(mapper.mapDomainObjectToEntity(product));
        return mapper.mapEntityToDomain(productEntity);
    }


    @Override
    public List<IndexInformation> getProducts() {
        return elasticsearchOperations.indexOps(ProductEntity.class).getInformation();
    }

    @Override
    public List<Product> fetchProductsBySimilarDescription(String title, String description) {
        MoreLikeThisQuery fullTextQuery = queriesBuilder.fullTextQuery(title, description);
        Query nativeQuery = NativeQuery.builder().withQuery(fullTextQuery._toQuery()).build();
        return elasticsearchOperations.search(nativeQuery, ProductEntity.class).stream()
                .map(SearchHit::getContent)
                .map(mapper::mapEntityToDomain)
                .toList();
    }

    @Override
    public Optional<Product> fetchProductById(String id) {
        ProductEntity result = elasticsearchOperations.get(id, ProductEntity.class);
        return result != null ? Optional.of(mapper.mapEntityToDomain(result)) : Optional.empty();
    }


}
