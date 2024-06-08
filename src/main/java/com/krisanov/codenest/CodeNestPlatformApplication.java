package com.krisanov.codenest;

import com.krisanov.codenest.config.AllowedCorsProperties;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SecurityScheme(
    name = "Bearer JWT",
    type = SecuritySchemeType.HTTP,
    scheme = "bearer",
    bearerFormat = "JWT",
    description = "Bearer JWT token.")
@SpringBootApplication
@EnableConfigurationProperties(AllowedCorsProperties.class)
public class CodeNestPlatformApplication {

  public static void main(String[] args) {
    SpringApplication.run(CodeNestPlatformApplication.class, args);
  }
}