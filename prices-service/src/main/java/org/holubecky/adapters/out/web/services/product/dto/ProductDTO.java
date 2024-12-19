package org.holubecky.adapters.out.web.services.product.dto;

import org.holubecky.application.ports.out.web.dto.LocationDTO;

public record ProductDTO(String id, String title, String description, LocationDTO location) {
}
