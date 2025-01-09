package org.holubecky.adapters.out.web.geocoding.dto;

public record ForwardGeocodingApiResponse(String place_id, Double lat, Double lon, String display_name) {
}
