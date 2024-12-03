package org.holubecky.application.ports.out.persistance;

import org.holubecky.application.domain.entities.Price;

public interface PricesCreationPort {
    Price createPrice(Price price);
}
