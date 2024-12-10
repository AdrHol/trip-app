package org.holubecky.configuration.providers;

import org.holubecky.adapters.out.persistance.repository.ProductEntity;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;

@Configuration
public class DataProvider {

    @Bean
    public CommandLineRunner dataLoader(ElasticsearchOperations elasticsearchOperations){
        return e -> {
            ProductEntity product = new ProductEntity();

            product.setTitle("Elephant pants");
            product.setDescription("Cool pants with elephants, to be cool tourist");

            elasticsearchOperations.save(product);
        };
    }
}
