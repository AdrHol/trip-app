package com.example.trip_app.prices_service.adapters.out.cache;

import com.example.trip_app.prices_service.application.domain.entities.Price;
import com.example.trip_app.prices_service.application.ports.out.cache.CacheServicePort;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CacheAdapter implements CacheServicePort {
    @Override
    public List<Price> getRecentlyViewedPrices() {
        return null;
    }

    @Override
    public void updateCache(List<Price> prices) {

    }
}
