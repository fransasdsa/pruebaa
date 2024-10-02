// src/main/java/com/upu/msstudentservice/service/StudentService.java
package com.upu.msstudentservice.service;

import com.upu.msstudentservice.model.Student;
import com.upu.msstudentservice.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;

@Service
public class StudentService {

    @Autowired
    private StudentRepository studentRepository;

    public Mono<Student> createStudent(Student student) {
        student.setId(UUID.randomUUID());
        student.setStatus("Active");
        return studentRepository.save(student);
    }

    public Mono<Student> getStudentById(UUID id) {
        return studentRepository.findById(id);
    }

    public Flux<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    public Flux<Student> getStudentsByLastName(String lastName) {
        return studentRepository.findByLastName(lastName);
    }

    public Flux<Student> getStudentsByProgram(String program) {
        return studentRepository.findByProgram(program);
    }

    public Flux<Student> getStudentsByStatus(String status) {
        return studentRepository.findByStatus(status);
    }

    public Mono<Student> updateStudent(UUID id, Student student) {
        return studentRepository.findById(id)
                .flatMap(existingStudent -> {
                    existingStudent.setFirstName(student.getFirstName());
                    existingStudent.setLastName(student.getLastName());
                    existingStudent.setEmail(student.getEmail());
                    existingStudent.setIdentificationNumber(student.getIdentificationNumber());
                    existingStudent.setDateOfBirth(student.getDateOfBirth());
                    existingStudent.setAddress(student.getAddress());
                    existingStudent.setPhoneNumber(student.getPhoneNumber());
                    existingStudent.setProgram(student.getProgram());
                    existingStudent.setEnrollmentDate(student.getEnrollmentDate());
                    existingStudent.setStatus(student.getStatus());
                    return studentRepository.save(existingStudent);
                });
    }

    public Mono<Void> deleteStudent(UUID id) {
        return studentRepository.deleteById(id);
    }
}
