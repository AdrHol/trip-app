package org.holubecky.adapters.out.persistance.repositories;

import org.holubecky.application.domain.entities.Price;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PricesRepository extends JpaRepository<PriceEntity, Long> {

    @Query("SELECT p FROM Price p WHERE p.longitude =:long AND p.latitude=:lat")
    List<PriceEntity> getAllPricesByCoordinates(@Param("long") String longitude, @Param("lat") String latitude);

    @Query("SELECT p from Price p WHERE p.country =:country AND p.city=:city")
    List<PriceEntity> getAllPricesByCityAndCountry(@Param("country") String country, @Param("city") String city);
}
