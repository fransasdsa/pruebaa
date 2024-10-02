// src/main/java/com/upu/mspaymentservice/repository/PaymentRepository.java
package com.upu.mspaymentservice.repository;

import com.upu.mspaymentservice.model.Payment;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;

import java.util.UUID;

public interface PaymentRepository extends ReactiveCrudRepository<Payment, UUID> {
    Flux<Payment> findByStudentId(UUID studentId);
}
