// src/main/java/com/upu/msstudentservice/repository/StudentRepository.java
package com.upu.msstudentservice.repository;

import com.upu.msstudentservice.model.Student;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

import java.util.UUID;

public interface StudentRepository extends ReactiveCrudRepository<Student, UUID> {
}
