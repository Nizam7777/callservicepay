package org.example.gudilin.callservicepay.controllers;

import org.example.gudilin.callservicepay.Model.Payment;
import org.example.gudilin.callservicepay.Proxy.PaymentsProxy;
import org.example.gudilin.callservicepay.Proxy.PaymentsProxyRestTemplate;
import org.example.gudilin.callservicepay.Proxy.PaymentsProxyWebClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.example.gudilin.callservicepay.Model.logging;
import reactor.core.publisher.Mono;

import java.util.UUID;

@RestController
public class PaymentsController {
    private final PaymentsProxy paymentsProxy;
    private final logging logging;

    @Autowired
    private PaymentsProxyRestTemplate paymentsProxyRestTemplate;

    @Autowired
    private PaymentsProxyWebClient paymentsProxyWebClient;

    public PaymentsController(PaymentsProxy paymentsProxy, logging logging) {
        this.paymentsProxy = paymentsProxy;
        this.logging = logging;
    }

    @PostMapping("/payment")
    public ResponseEntity<Payment> createPayment2(
            @RequestBody Payment payment
    ){
        logging.getInfo("Component Received Id = " + payment.getAmount());
        String RequestId = UUID.randomUUID().toString();
        return paymentsProxy.createPayment( RequestId, payment);
    }

    @PostMapping("/paymentRestTemplate")
    public Payment paymentRestTemplate(
            @RequestBody Payment payment
    ){
        logging.getInfo("Component Received Id = " + payment.getAmount());
        return paymentsProxyRestTemplate.createPayment(payment);
    }

    @PostMapping("/paymentWebClient")
    public Mono<Payment> paymentWebClient(
            @RequestBody Payment payment
    ){
        logging.getInfo("WebClient Component Received Id = " + payment.getAmount());
        String RequestId = UUID.randomUUID().toString();
        return paymentsProxyWebClient.createPayment(RequestId, payment);
    }
}
