package org.holubecky.application.ports.out.cache;

import org.holubecky.application.domain.entities.Price;

import java.util.List;

public interface CacheServicePort {

    List<Price> getRecentlyViewedPrices();
    void updateCache(List<Price> prices);
}
