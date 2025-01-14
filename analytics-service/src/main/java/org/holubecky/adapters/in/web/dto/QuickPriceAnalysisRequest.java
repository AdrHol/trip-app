package org.holubecky.adapters.in.web.dto;

public record QuickPriceAnalysisRequest(String productId, String currency, Double price, Coordinates coordinates) {
}
