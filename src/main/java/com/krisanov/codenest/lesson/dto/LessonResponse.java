package com.krisanov.codenest.lesson.dto;

import lombok.Builder;

import java.util.List;

@Builder
public record LessonResponse(
        Long lessonId,
        String title,
        String description,
        List<LessonContentResponse> content
) {

}
