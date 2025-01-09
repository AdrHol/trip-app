package org.holubecky.adapters.out.persistance;


import co.elastic.clients.elasticsearch._types.query_dsl.*;
import lombok.RequiredArgsConstructor;
import org.holubecky.adapters.out.persistance.repositories.PriceEntity;
import org.holubecky.adapters.out.persistance.repositories.PricesElasticRepository;
import org.holubecky.adapters.out.persistance.repositories.query.ElasticsearchQueryBase;
import org.holubecky.application.domain.entities.Price;
import org.holubecky.application.domain.entities.mappers.PriceMapper;
import org.holubecky.application.ports.out.persistance.PricesCreationPort;
import org.holubecky.application.ports.out.persistance.PricesRetrievalPort;
import org.springframework.data.elasticsearch.client.elc.NativeQuery;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.SearchHit;
import org.springframework.data.elasticsearch.core.query.Query;
import org.springframework.stereotype.Component;

import java.lang.annotation.Native;
import java.util.List;
import java.util.stream.Stream;

@Component
@RequiredArgsConstructor
public class PricesAdapter implements PricesRetrievalPort, PricesCreationPort {

    private final PricesElasticRepository pricesElasticRepository;
    private final ElasticsearchOperations elasticsearchOperations;
    private final PriceMapper priceMapper;
    private final ElasticsearchQueryBase queryBase;


    @Override
    public List<Price> getAllPrices() {
        MatchAllQuery matchAll = MatchAllQuery.of(query-> query.queryName("all"));
        Query nativeQuery = NativeQuery.builder().withQuery(matchAll._toQuery()).build();
        return elasticsearchOperations.search(nativeQuery, PriceEntity.class).stream().map(SearchHit::getContent).map(priceMapper::priceEntityToDomainModel).toList();
    }

    @Override
    public List<Price> getPricesByCords(Double longitude, Double latitude, String productId) {
        TermQuery productIdMatchQuery = queryBase.productIdQuery(productId);
        GeoDistanceQuery distanceQuery = queryBase.findInArea(longitude,latitude, "5km");
        NestedQuery nestedQuery = NestedQuery.of(nested -> nested.path("locationEntity").query(distanceQuery._toQuery()));
        BoolQuery bool = BoolQuery.of(query -> query.must(productIdMatchQuery._toQuery()).filter(nestedQuery._toQuery()));

        Query nativeQuery = NativeQuery.builder()
                .withQuery(bool._toQuery())
                .build();

        return elasticsearchOperations.search(nativeQuery, PriceEntity.class).stream()
                .map(SearchHit::getContent)
                .map(priceMapper::priceEntityToDomainModel).toList();
    }

    @Override
    public List<Price> getPricesByLocation(String country, String city) {
        TermQuery matchCity = queryBase.matchCity(city);
        TermQuery matchCountry = queryBase.matchCountry(country);

        BoolQuery boolQuery = BoolQuery.of(query -> query.must(
                matchCity._toQuery(),
                matchCountry._toQuery()));
        NestedQuery nestedQuery = NestedQuery.of(nested -> nested
                .path("locationEntity")
                .query(boolQuery._toQuery()));
        Query nativeQuery = NativeQuery.builder().withQuery(nestedQuery._toQuery()).build();

        return elasticsearchOperations.search(nativeQuery, PriceEntity.class).stream()
                .map(SearchHit::getContent)
                .map(priceMapper::priceEntityToDomainModel)
                .toList();
    }

    @Override
    public List<Price> getPricesByProductId(String productId) {
        TermQuery productIdQuery = queryBase.productIdQuery(productId);
        Query nativeQuery = NativeQuery.builder().withQuery(productIdQuery._toQuery()).build();

        return elasticsearchOperations.search(nativeQuery, PriceEntity.class).stream()
                .map(SearchHit::getContent)
                .map(priceMapper::priceEntityToDomainModel)
                .toList();
    }

    @Override
    public Price createPrice(Price price) {
        PriceEntity priceEntity = priceMapper.priceDomainModelToEntity(price);
        return priceMapper.priceEntityToDomainModel(pricesElasticRepository.save(priceEntity));
    }
}
