package com.krisanov.codenest.lesson.dto;

import com.krisanov.codenest.common.dto.ImageRequest;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.List;

public record LessonRequest(
        @Size(min = 10, max = 40, message = "Title must be between 10 and 40 characters.") String title,
        @Size(min = 10, max = 100, message = "Title must be between 10 and 100 characters.") String description,
        @NotNull(message = "Image must not be null.") ImageRequest image,
        @NotNull(message = "Content must not be null.") List<LessonContentRequest> content
) {

}
