package org.holubecky.adapters.out.persistance;

import ch.qos.logback.core.util.StringUtil;
import co.elastic.clients.elasticsearch._types.LatLonGeoLocation;
import co.elastic.clients.elasticsearch._types.query_dsl.BoolQuery;
import co.elastic.clients.elasticsearch._types.query_dsl.GeoDistanceQuery;
import co.elastic.clients.elasticsearch._types.query_dsl.Like;
import co.elastic.clients.elasticsearch._types.query_dsl.MoreLikeThisQuery;
import lombok.RequiredArgsConstructor;
import org.holubecky.adapters.out.persistance.repository.LocationEntity;
import org.holubecky.adapters.out.persistance.repository.QueriesBuilder;
import org.holubecky.application.domain.model.Location;
import org.holubecky.application.domain.model.Product;
import org.holubecky.adapters.out.persistance.repository.ProductEntity;
import org.holubecky.application.ports.out.persistance.CreateProductPort;
import org.holubecky.application.ports.out.persistance.RetrieveProductPort;
import org.springframework.data.elasticsearch.client.elc.NativeQuery;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.IndexInformation;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.data.elasticsearch.core.geo.GeoPoint;
import org.springframework.data.elasticsearch.core.query.Query;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class ElasticSearchAdapter implements CreateProductPort, RetrieveProductPort {

    private final ElasticsearchOperations elasticsearchOperations;
    private final QueriesBuilder queriesBuilder;
    @Override
    public List<Product> fetchSimilarProducts(Product product) {
        MoreLikeThisQuery mlt = queriesBuilder.fullTextQuery(product.getTitle(), product.getDescription());
        GeoDistanceQuery geoQuery = queriesBuilder.findInArea(product.getLocation().getLon(),
                                                            product.getLocation().getLat(),
                                                            "2km");

        BoolQuery andQuery = BoolQuery.of(q -> q.must(mlt._toQuery()).must(geoQuery._toQuery()));
        Query nativeQuery = NativeQuery.builder().withQuery(andQuery._toQuery()).build();

        SearchHits<ProductEntity> result = elasticsearchOperations.search(nativeQuery, ProductEntity.class);

        return null;
    }

    @Override
    public Product saveProduct(Product product) {
        ProductEntity productEntity = elasticsearchOperations.save(mapDomainObjectToEntity(product));
        return mapEntityToDomain(productEntity);
    }


    @Override
    public List<IndexInformation> getProducts() {
        return elasticsearchOperations.indexOps(ProductEntity.class).getInformation();
    }

    private ProductEntity mapDomainObjectToEntity(Product product){
        ProductEntity productEntity = new ProductEntity();
        String id = product.getId();

        if(StringUtil.isNullOrEmpty(id)) {
            productEntity.setId(id);
        }
        productEntity.setTitle(product.getTitle());
        productEntity.setDescription(product.getDescription());
        productEntity.setLocationEntity(
                new LocationEntity(product.getLocation().getCity(),
                                    product.getLocation().getCountry(),
                                    new GeoPoint(product.getLocation().getLat(),
                                                 product.getLocation().getLon())));

        return productEntity;
    }

    private Product mapEntityToDomain(ProductEntity productEntity){
        Product product = new Product();
        product.setId(productEntity.getId());
        product.setTitle(productEntity.getTitle());
        product.setDescription(productEntity.getDescription());
        product.setLocation(Location.builder().city(productEntity.getLocationEntity().getCity())
                .country(productEntity.getLocationEntity().getCountry())
                .lat(productEntity.getLocationEntity().getCoordinates().getLat())
                .lon(productEntity.getLocationEntity().getCoordinates().getLon()).build());

        return product;
    }

}
