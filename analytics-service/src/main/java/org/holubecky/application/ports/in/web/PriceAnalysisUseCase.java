package org.holubecky.application.ports.in.web;

import org.holubecky.adapters.in.web.dto.QuickPriceAnalysisRequest;

public interface PriceAnalysisUseCase {

    void analyzePrice(QuickPriceAnalysisRequest request);
}
