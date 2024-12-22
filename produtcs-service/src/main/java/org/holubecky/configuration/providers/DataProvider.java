package org.holubecky.configuration.providers;

import org.holubecky.adapters.out.persistance.repository.entity.LocationEntity;
import org.holubecky.adapters.out.persistance.repository.entity.ProductEntity;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.geo.GeoPoint;

@Configuration
public class DataProvider {

    @Bean
    public CommandLineRunner dataLoader(ElasticsearchOperations elasticsearchOperations){
        return e -> {
//            ProductEntity product = new ProductEntity();
//
//            product.setTitle("Figurki z krasnalami");
//            product.setDescription("Srajace krasnale");
//            product.setLocationEntity(LocationEntity.builder().city("Wrocław")
//                    .country("Polska")
//                    .coordinates(new GeoPoint(51.107624,17.033733)).build());
//            elasticsearchOperations.save(product);
        };
    }
}
