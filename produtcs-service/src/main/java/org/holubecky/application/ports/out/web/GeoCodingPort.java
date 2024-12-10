package org.holubecky.application.ports.out.web;

import org.holubecky.application.domain.model.Location;

public interface GeoCodingPort {
    Location getLocationByCoordinates(String lon, String lat);
    Location getCoordinatesByLocation(String city, String country);
}