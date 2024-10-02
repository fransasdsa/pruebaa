// src/main/java/com/upu/mspaymentservice/controller/PaymentController.java
package com.upu.mspaymentservice.controller;

import com.upu.mspaymentservice.model.Payment;
import com.upu.mspaymentservice.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;

@RestController
@RequestMapping("/payments")
public class PaymentController {

    @Autowired
    private PaymentService paymentService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<Payment> createPayment(@RequestBody Payment payment) {
        return paymentService.createPayment(payment);
    }

    @GetMapping("/{id}")
    public Mono<Payment> getPaymentById(@PathVariable UUID id) {
        return paymentService.getPaymentById(id);
    }

    @GetMapping("/student/{studentId}")
    public Flux<Payment> getPaymentsByStudentId(@PathVariable UUID studentId) {
        return paymentService.getPaymentsByStudentId(studentId);
    }

    @GetMapping
    public Flux<Payment> getAllPayments() {
        return paymentService.getAllPayments();
    }
}
