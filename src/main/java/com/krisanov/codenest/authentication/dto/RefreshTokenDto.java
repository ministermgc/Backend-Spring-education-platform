package com.krisanov.codenest.authentication.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;

@Builder
public record RefreshTokenDto(@NotNull String refreshToken) {

}
