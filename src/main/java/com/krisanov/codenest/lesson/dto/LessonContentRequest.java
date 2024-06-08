package com.krisanov.codenest.lesson.dto;

import jakarta.annotation.Nullable;
import jakarta.validation.constraints.NotBlank;

public record LessonContentRequest(
        @Nullable Long contentId,
        @NotBlank(message = "Paragraph must not be blank.") String paragraph,
        @Nullable String code,
        @Nullable String codeLanguage,
        @Nullable Long lessonId
) {

}
