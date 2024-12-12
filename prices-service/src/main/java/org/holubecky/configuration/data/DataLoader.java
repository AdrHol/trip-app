package org.holubecky.configuration.data;

import org.holubecky.adapters.out.persistance.repositories.PricesRepository;
import org.holubecky.application.domain.entities.Cost;
import org.holubecky.application.domain.entities.Price;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDateTime;

@Configuration
public class DataLoader {

    @Bean
    public CommandLineRunner loadData(PricesRepository pricesRepository){
        return args -> {
            Price price1 = new Price();
            price1.setUserId("user1");
            price1.setProductId("Product A");
            price1.setPostedAt(LocalDateTime.now());
            price1.setCost(new Cost("USD", 100));

            Price price2 = new Price();
            price2.setUserId("user2");
            price2.setProductId("Product B");
            price2.setPostedAt(LocalDateTime.now().minusDays(1));
            price2.setCost(new Cost("EUR", 150));

            pricesRepository.save(price1);
            pricesRepository.save(price2);

        };
    }
}
