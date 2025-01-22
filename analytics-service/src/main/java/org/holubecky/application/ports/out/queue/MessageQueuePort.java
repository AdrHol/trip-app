package org.holubecky.application.ports.out.queue;

import org.holubecky.adapters.in.web.dto.QuickPriceAnalysisRequest;

public interface MessageQueuePort {
    void sendPriceCreationMessage(QuickPriceAnalysisRequest request);
}
