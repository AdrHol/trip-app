package org.holubecky.configuration.feign;

import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableFeignClients("org.holubecky.adapters.out.web.geocoding.feignClient")
public class FeignConfig {
}
