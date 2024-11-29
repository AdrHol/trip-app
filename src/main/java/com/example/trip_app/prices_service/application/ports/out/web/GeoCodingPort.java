package com.example.trip_app.prices_service.application.ports.out.web;

import com.example.trip_app.prices_service.application.domain.entities.Location;

public interface GeoCodingPort {
    Location getLocationByCoordinates(String lon, String lat);
    Location getCoordinatesByLocation(String city, String country);
}
