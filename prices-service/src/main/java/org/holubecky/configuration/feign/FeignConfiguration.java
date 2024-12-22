package org.holubecky.configuration.feign;

import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableFeignClients(basePackages = "org.holubecky.adapters.out.web")
public class FeignConfiguration {
}
