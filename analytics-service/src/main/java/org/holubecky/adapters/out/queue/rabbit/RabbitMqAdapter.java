package org.holubecky.adapters.out.queue.rabbit;

import org.holubecky.adapters.in.web.dto.QuickPriceAnalysisRequest;
import org.holubecky.application.ports.out.queue.MessageQueuePort;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.stereotype.Component;

@Component
public class RabbitMqAdapter implements MessageQueuePort {

    private final StreamBridge streamBridge;
    private final String CREATE_PRICE_BINDING = "analyze-price-out";

    public RabbitMqAdapter(StreamBridge streamBridge){
        this.streamBridge = streamBridge;
    }

    @Override
    public void sendPriceCreationMessage(QuickPriceAnalysisRequest request) {
        System.out.println("Sending message");
        streamBridge.send(CREATE_PRICE_BINDING, request);
    }
}
