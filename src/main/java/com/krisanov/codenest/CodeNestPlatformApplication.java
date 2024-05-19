package com.krisanov.codenest;

import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SecurityScheme(
    name = "Bearer JWT",
    type = SecuritySchemeType.HTTP,
    scheme = "bearer",
    bearerFormat = "JWT",
    description = "Bearer JWT token.")
@SpringBootApplication
public class CodeNestPlatformApplication {

  public static void main(String[] args) {
    SpringApplication.run(CodeNestPlatformApplication.class, args);
  }
}