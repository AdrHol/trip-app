package org.holubecky.configuration.data;

import org.holubecky.adapters.out.persistance.repositories.PriceEntity;
import org.holubecky.adapters.out.persistance.repositories.PricesElasticRepository;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDateTime;

@Configuration
public class DataLoader {

    @Bean
    public CommandLineRunner loadData(PricesElasticRepository pricesSQLRepository){
        return args -> {
        };
    }
}
