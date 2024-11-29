package com.example.trip_app.prices_service.application.ports.out.cache;

import com.example.trip_app.prices_service.application.domain.entities.Price;

import java.util.List;

public interface CacheServicePort {

    List<Price> getRecentlyViewedPrices();
    void updateCache(List<Price> prices);
}
