package org.holubecky.adapters.out.web.dto;

public record ReverseGeocodingResponse(String lon, String lat, Address address) {
}
