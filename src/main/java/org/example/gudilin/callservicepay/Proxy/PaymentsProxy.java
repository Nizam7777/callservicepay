package org.example.gudilin.callservicepay.Proxy;

import org.example.gudilin.callservicepay.Model.Payment;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(name = "payments", url = "${name.service.url}")
public interface PaymentsProxy {
    @PostMapping("/payment")
    ResponseEntity<Payment> createPayment(
            @RequestHeader String RequestId,
            @RequestBody Payment payment);
}