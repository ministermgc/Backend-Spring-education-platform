package com.krisanov.codenest.authentication.dto;

import lombok.Builder;

@Builder
public record AuthenticationResponseDto(Long userId, String accessToken, String refreshToken) {

}
