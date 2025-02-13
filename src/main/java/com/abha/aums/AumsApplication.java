package com.abha.aums;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Entry point for the AUMS (Abha User Management System) application.
 * This class bootstraps the Spring Boot application.
 */
@SpringBootApplication
public class AumsApplication {
  /**
   * Main method to start the Spring Boot application.
   *
   * @param args Command-line arguments passed to the application.
   */
  public static void main(String[] args) {
    SpringApplication.run(AumsApplication.class, args);
  }

}
