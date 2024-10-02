// src/main/java/com/upu/msthesisservice/MsThesisServiceApplication.java
package com.upu.msthesisservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients // Habilita Feign Clients para comunicación con otros servicios
public class MsThesisServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(MsThesisServiceApplication.class, args);
    }
}
