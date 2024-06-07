package com.krisanov.codenest.common.dto;

import jakarta.validation.constraints.NotNull;

public record ImageRequest(@NotNull(message = "The image data must not be null.") byte[] data) {
}
