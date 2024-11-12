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

import java.util.UUID;

@Component
public class PaymentsProxyRestTemplate {
    private final RestTemplate rest;

    @Autowired
    private logging logging;

    @Value("${name.service.url}")
    private String uri_service;

    public PaymentsProxyRestTemplate(RestTemplate rest) {
        this.rest = rest;
    }

    public Payment createPayment(Payment payment){
        String uri = "http://" + uri_service + "/payment";

        logging.getInfo("uri_service = " + uri_service);
        HttpHeaders header;
        header = new HttpHeaders();

        header.add("RequestId","Headerrrrrr" + UUID.randomUUID().toString());

        HttpEntity<Payment> httpEntity = new HttpEntity<>(payment,header);

        ResponseEntity<Payment> response =
                rest.exchange(uri, HttpMethod.POST, httpEntity, Payment.class);

        return response.getBody();
    }
}
