package com.example.trip_app.prices_service.configuration.data;

import com.example.trip_app.prices_service.adapters.out.persistance.repositories.PricesRepository;
import com.example.trip_app.prices_service.application.domain.entities.Cost;
import com.example.trip_app.prices_service.application.domain.entities.Location;
import com.example.trip_app.prices_service.application.domain.entities.Price;
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
            price1.setProduct("Product A");
            price1.setPostedAt(LocalDateTime.now());
            price1.setCost(new Cost("USD", 100));
            price1.setLocation(new Location("40.7128", "-74.0060", "New York", "USA"));

            Price price2 = new Price();
            price2.setUserId("user2");
            price2.setProduct("Product B");
            price2.setPostedAt(LocalDateTime.now().minusDays(1));
            price2.setCost(new Cost("EUR", 150));
            price2.setLocation(new Location("48.8566", "2.3522", "Paris", "France"));

            pricesRepository.save(price1);
            pricesRepository.save(price2);

        };
    }
}
