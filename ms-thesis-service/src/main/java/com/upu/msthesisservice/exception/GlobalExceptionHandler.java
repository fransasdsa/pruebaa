// src/main/java/com/upu/msthesisservice/exception/GlobalExceptionHandler.java
package com.upu.msthesisservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import reactor.core.publisher.Mono;
import org.springframework.web.bind.support.WebExchangeBindException;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(WebExchangeBindException.class)
    public Mono<ErrorResponse> handleValidationException(WebExchangeBindException ex) {
        StringBuilder errors = new StringBuilder();
        ex.getFieldErrors().forEach(error ->
                errors.append(error.getField()).append(": ").append(error.getDefaultMessage()).append("; ")
        );
        return Mono.just(new ErrorResponse("VALIDATION_ERROR", errors.toString()))
                .flatMap(response -> Mono.just(response));
    }

    @ExceptionHandler(RuntimeException.class)
    public Mono<ErrorResponse> handleRuntimeException(RuntimeException ex) {
        return Mono.just(new ErrorResponse("INTERNAL_SERVER_ERROR", ex.getMessage()))
                .flatMap(response -> Mono.just(response));
    }

    // Clase interna para la respuesta de error
    public static class ErrorResponse {
        private String error;
        private String message;

        public ErrorResponse() {}

        public ErrorResponse(String error, String message) {
            this.error = error;
            this.message = message;
        }

        // Getters y Setters

        public String getError() {
            return error;
        }

        public void setError(String error) {
            this.error = error;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }
    }
}
