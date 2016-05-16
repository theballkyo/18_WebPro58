package com.pl;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.aspectj.EnableSpringConfigured;

@SpringBootApplication
@EnableSpringConfigured
public class LeavesystemApplication {

    public static void main(String[] args) {
        SpringApplication.run(LeavesystemApplication.class, args);
    }
}
