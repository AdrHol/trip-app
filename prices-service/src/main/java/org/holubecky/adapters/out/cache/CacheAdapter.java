package org.holubecky.adapters.out.cache;


import org.holubecky.application.domain.entities.Price;
import org.holubecky.application.ports.out.cache.CacheServicePort;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CacheAdapter implements CacheServicePort {


    public List<Price> getRecentlyViewedPrices() {
        return null;
    }

    public void updateCache(List<Price> prices) {

    }
}
