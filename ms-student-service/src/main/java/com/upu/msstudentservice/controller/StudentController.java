// src/main/java/com/upu/msstudentservice/controller/StudentController.java
package com.upu.msstudentservice.controller;

import com.upu.msstudentservice.model.Student;
import com.upu.msstudentservice.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;

@RestController
@RequestMapping("/students")
public class StudentController {

    @Autowired
    private StudentService studentService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<Student> createStudent(@RequestBody Student student) {
        return studentService.createStudent(student);
    }

    @GetMapping("/{id}")
    public Mono<Student> getStudentById(@PathVariable UUID id) {
        return studentService.getStudentById(id);
    }

    @GetMapping
    public Flux<Student> getAllStudents() {
        return studentService.getAllStudents();
    }

    @GetMapping("/last-name/{lastName}")
    public Flux<Student> getStudentsByLastName(@PathVariable String lastName) {
        return studentService.getStudentsByLastName(lastName);
    }

    @GetMapping("/program/{program}")
    public Flux<Student> getStudentsByProgram(@PathVariable String program) {
        return studentService.getStudentsByProgram(program);
    }

    @GetMapping("/status/{status}")
    public Flux<Student> getStudentsByStatus(@PathVariable String status) {
        return studentService.getStudentsByStatus(status);
    }

    @PutMapping("/{id}")
    public Mono<Student> updateStudent(@PathVariable UUID id, @RequestBody Student student) {
        return studentService.updateStudent(id, student);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public Mono<Void> deleteStudent(@PathVariable UUID id) {
        return studentService.deleteStudent(id);
    }
}
