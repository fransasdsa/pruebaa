// src/main/java/com/upu/mspaymentservice/service/PaymentService.java
package com.upu.mspaymentservice.service;

import com.stripe.exception.StripeException;
import com.stripe.model.PaymentIntent;
import com.stripe.param.PaymentIntentCreateParams;
import com.upu.mspaymentservice.model.Payment;
import com.upu.mspaymentservice.repository.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
public class PaymentService {

    @Autowired
    private PaymentRepository paymentRepository;

    public Mono<Payment> createPayment(Payment payment) {
        payment.setId(UUID.randomUUID());
        payment.setStatus("PENDING");
        payment.setCreatedAt(LocalDateTime.now());
        payment.setUpdatedAt(LocalDateTime.now());

        return paymentRepository.save(payment)
                .flatMap(savedPayment -> processPayment(savedPayment));
    }

    private Mono<Payment> processPayment(Payment payment) {
        return Mono.fromCallable(() -> {
            PaymentIntentCreateParams params =
                    PaymentIntentCreateParams.builder()
                            .setAmount((long) (payment.getAmount() * 100))
                            .setCurrency(payment.getCurrency())
                            .setDescription(payment.getDescription())
                            .build();

            PaymentIntent intent = PaymentIntent.create(params);
            payment.setStatus("COMPLETED");
            payment.setUpdatedAt(LocalDateTime.now());
            return paymentRepository.save(payment).block();
        }).onErrorResume(e -> {
            payment.setStatus("FAILED");
            payment.setUpdatedAt(LocalDateTime.now());
            return paymentRepository.save(payment);
        });
    }

    public Mono<Payment> getPaymentById(UUID id) {
        return paymentRepository.findById(id);
    }

    public Flux<Payment> getPaymentsByStudentId(UUID studentId) {
        return paymentRepository.findByStudentId(studentId);
    }

    public Flux<Payment> getAllPayments() {
        return paymentRepository.findAll();
    }
}
