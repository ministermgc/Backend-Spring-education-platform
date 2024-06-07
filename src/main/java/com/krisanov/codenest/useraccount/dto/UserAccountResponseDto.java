package com.krisanov.codenest.useraccount.dto;

import lombok.Builder;


@Builder
public record UserAccountResponseDto(
        String username,
        String email,
        String firstName,
        String lastName
) {

}
