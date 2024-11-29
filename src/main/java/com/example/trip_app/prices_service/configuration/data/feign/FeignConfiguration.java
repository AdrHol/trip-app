package com.example.trip_app.prices_service.configuration.data.feign;

import org.springframework.cloud.openfeign.CollectionFormat;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableFeignClients(basePackages = "com.example.trip_app.prices_service.adapters.out.web")
public class FeignConfiguration {
}
