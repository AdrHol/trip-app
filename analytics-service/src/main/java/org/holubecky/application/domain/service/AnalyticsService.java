package org.holubecky.application.domain.service;

import org.holubecky.adapters.in.web.dto.QuickPriceAnalysisRequest;
import org.holubecky.application.ports.in.web.DecodeImageUseCase;
import org.holubecky.application.ports.in.web.PriceAnalysisUseCase;
import org.holubecky.application.ports.out.queue.MessageQueuePort;
import org.springframework.stereotype.Service;

@Service
public class AnalyticsService implements DecodeImageUseCase, PriceAnalysisUseCase {

    private final MessageQueuePort messageQueuePort;

    public AnalyticsService(MessageQueuePort messageQueuePort){
        this.messageQueuePort = messageQueuePort;
    }


    @Override
    public void analyzePrice(QuickPriceAnalysisRequest request) {
        messageQueuePort.sendPriceCreationMessage(request);
    }
}
