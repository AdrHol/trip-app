package com.example.trip_app.prices_service.adapters.out.persistance.repositories;

import com.example.trip_app.prices_service.application.domain.entities.Price;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PricesRepository extends JpaRepository<Price, Long> {
}
