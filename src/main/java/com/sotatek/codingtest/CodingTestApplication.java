package com.sotatek.codingtest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.web.servlet.MultipartAutoConfiguration;

@SpringBootApplication(exclude = {MultipartAutoConfiguration.class})
public class CodingTestApplication {

    public static void main(String[] args) {
        SpringApplication.run(CodingTestApplication.class, args);
    }

}
