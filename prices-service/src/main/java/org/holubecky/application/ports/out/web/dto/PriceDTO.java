package org.holubecky.application.ports.out.web.dto;


import org.holubecky.application.domain.entities.Cost;

public record PriceDTO(String userId, String productId, LocationDTO location ,Cost price) {
}
