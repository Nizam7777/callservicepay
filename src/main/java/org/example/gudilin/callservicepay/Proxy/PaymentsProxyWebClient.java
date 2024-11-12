package org.example.gudilin.callservicepay.Proxy;

import org.example.gudilin.callservicepay.Model.Payment;
import org.example.gudilin.callservicepay.Model.logging;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.UUID;

@Component
public class PaymentsProxyWebClient {
    private final WebClient webClient;

    @Autowired
    private logging logging;

    @Value("${name.service.url}")
    private String uri_service;

    public PaymentsProxyWebClient(WebClient webClient) {
        this.webClient = webClient;
    }

    public Mono<Payment> createPayment(
            String  RequestId,
            Payment payment){
        String uri = "http://" + uri_service + "/payment";

        logging.getInfo("uri_service = " + uri_service);
        RequestId = RequestId + " ; WebClient";

        return webClient.post()
                .uri(uri)
                .header("RequestId", RequestId)
                .body(Mono.just(payment), Payment.class)
                .retrieve()
                .bodyToMono(Payment.class);
    }
}
