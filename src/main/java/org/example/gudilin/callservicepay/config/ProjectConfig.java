package org.example.gudilin.callservicepay.config;

import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
@EnableFeignClients(basePackages = "org.example.gudilin.callservicepay.Proxy")
@ComponentScan(basePackages = {"org.example.gudilin.callservicepay.Model","org.example.gudilin.callservicepay.Proxy"})
public class ProjectConfig {
    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    @Bean
    public WebClient webClient() {
        return WebClient
                .builder() //Создаем бин WebClient и добавляем его в контекст Spring
                .build();
    }
}
