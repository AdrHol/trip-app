package org.holubecky.adapters.out.web.geocoding.dto;


public record ReverseGeocodingApiResponse(Double lon, Double lat, Address address) {
}
