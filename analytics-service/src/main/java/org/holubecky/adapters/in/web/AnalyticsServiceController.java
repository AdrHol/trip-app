package org.holubecky.adapters.in.web;


import lombok.RequiredArgsConstructor;
import org.holubecky.adapters.in.web.dto.QuickPriceAnalysisRequest;
import org.holubecky.adapters.in.web.dto.QuickPriceAnalysisResponse;
import org.holubecky.application.ports.in.web.PriceAnalysisUseCase;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/v1")
@RequiredArgsConstructor
public class AnalyticsServiceController {

    private final PriceAnalysisUseCase priceAnalysisUseCase;

    @PostMapping("/quick")
    public Mono<QuickPriceAnalysisResponse> quickPriceAnalysis(@RequestBody QuickPriceAnalysisRequest request){
        priceAnalysisUseCase.analyzePrice(request);

        return Mono.just(new QuickPriceAnalysisResponse(request.productId()));
    }

    @PostMapping
    public Mono<String> searchByImage(@RequestParam("file") MultipartFile image){
        return Mono.just("Hello ");
    }
}
