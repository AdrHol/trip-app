package org.holubecky.application.domain.service;

import org.holubecky.application.ports.in.web.DecodeImageUseCase;
import org.holubecky.application.ports.in.web.PriceAnalysisUseCase;
import org.springframework.stereotype.Service;

@Service
public class AnalyticsService implements DecodeImageUseCase, PriceAnalysisUseCase {
}
