package org.holubecky.application.ports.in.web.commands;


public record CreatePriceCommand(String userId, String product, String currency, long price, String city, String country,
                                 String longitude, String latitude) {
}
