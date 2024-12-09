package org.holubecky.adapters.out.persistance;




import co.elastic.clients.elasticsearch._types.query_dsl.Like;
import co.elastic.clients.elasticsearch._types.query_dsl.MoreLikeThisQuery;
import lombok.RequiredArgsConstructor;
import org.elasticsearch.synonyms.PagedResult;
import org.holubecky.adapters.out.persistance.repository.ElasticSearchRepository;
import org.holubecky.application.domain.entity.ProductEntity;
import org.holubecky.application.ports.in.web.dto.ProductCreationRequest;
import org.holubecky.application.ports.out.persistance.CreateProductPort;
import org.holubecky.application.ports.out.persistance.RetrieveProductPort;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
    public List<ProductEntity> fetchSimilarProducts(ProductCreationRequest request) {

        MoreLikeThisQuery query = MoreLikeThisQuery.of(e -> e.fields("title", "description")
                .like(new Like.Builder().text(request.title()).build(), new Like.Builder().text(request.description()).build())
                .minTermFreq(1)
                .analyzer("standard"));
        Query nativeQuery = NativeQuery.builder()
                .withQuery(query._toQuery()).build();


        SearchHits<ProductEntity> result = elasticsearchOperations.search(nativeQuery, ProductEntity.class);

        return null;
    }

    @Override
    public ProductEntity saveProduct(ProductEntity product) {
        ProductEntity result = elasticsearchOperations.save(product);
        return result;
    }


    @Override
    public List<IndexInformation> getProducts() {
        return elasticsearchOperations.indexOps(ProductEntity.class).getInformation();
    }

    private ProductEntity mapRequestToEntity(ProductCreationRequest productCreationRequest){
        ProductEntity product = new ProductEntity();
        product.setTitle(productCreationRequest.title());
        product.setDescription(productCreationRequest.description());
        return new ProductEntity();
    }
}
