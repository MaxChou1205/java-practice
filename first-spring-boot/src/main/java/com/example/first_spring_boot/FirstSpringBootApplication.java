package com.example.first_spring_boot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(
        scanBasePackages = {"com.example.first_spring_boot", "com.example.util"}
)
public class FirstSpringBootApplication {

  public static void main(String[] args) {
    SpringApplication.run(FirstSpringBootApplication.class, args);
  }

}
