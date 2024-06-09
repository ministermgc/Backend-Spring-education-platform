package com.krisanov.codenest.useraccount.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record UpdateUserAccountRequestDto(
        @Email @NotBlank @Size(min = 4, max = 50, message = "Email must be between 4 and 50 characters") String email,
        @NotBlank @Size(min = 2, max = 20, message = "First name must be between 2 and 20 characters") String firstName,
        @NotBlank @Size(min = 2, max = 20, message = "Last name must be between 2 and 20 characters") String lastName,
        @NotNull String password
) {

}
