package org.holubecky.application.ports.in.web.commands;


public record CreatePriceCommand(String userId, String productId, String currency, long price, Double lon, Double lat) {
}
