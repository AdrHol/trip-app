package org.holubecky.adapters.out.web.dto;

public record ForwardGeocodingApiResponse(String place_id, String lat, String lon, String display_name) {
}
