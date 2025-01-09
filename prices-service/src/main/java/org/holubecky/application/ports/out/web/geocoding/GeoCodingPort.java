package org.holubecky.application.ports.out.web.geocoding;


import org.holubecky.application.ports.out.web.geocoding.dto.Location;

public interface GeoCodingPort {
    Location getLocationByCoordinates(Double lon, Double lat);
    Location getCoordinatesByLocation(String city, String country);
}
