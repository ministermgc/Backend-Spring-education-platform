package com.krisanov.codenest.authentication.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record AuthenticationRequestDto(
        @NotBlank @Size(min = 4, max = 20, message = "Username must be between 4 and 20 characters") String username,
        @NotBlank @Size(min = 8, message = "Password must not be less then 8 characters") String password) {

}
