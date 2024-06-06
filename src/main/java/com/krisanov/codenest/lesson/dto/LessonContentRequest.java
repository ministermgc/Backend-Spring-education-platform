package com.krisanov.codenest.lesson.dto;

import jakarta.annotation.Nullable;
import jakarta.validation.constraints.NotBlank;

public record LessonContentRequest(
        @NotBlank(message = "Paragraph must not be blank.") String paragraph,
        @Nullable String code,
        @Nullable String codeLanguage
) {

}
