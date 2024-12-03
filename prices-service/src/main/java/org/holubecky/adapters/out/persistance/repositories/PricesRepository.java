package org.holubecky.adapters.out.persistance.repositories;

import org.holubecky.application.domain.entities.Price;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PricesRepository extends JpaRepository<Price, Long> {

    @Query("SELECT p FROM Price p WHERE p.location.longitude =:long AND p.location.latitude=:lat")
    List<Price> getAllPricesByCoordinates(@Param("long") String longitude, @Param("lat") String latitude);

    @Query("SELECT p from Price p WHERE p.location.country =:country AND p.location.city=:city")
    List<Price> getAllPricesByCityAndCountry(@Param("country") String country, @Param("city") String city);
}
