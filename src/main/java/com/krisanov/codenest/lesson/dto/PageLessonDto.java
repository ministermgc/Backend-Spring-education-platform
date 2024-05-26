package com.krisanov.codenest.lesson.dto;

import lombok.Builder;

@Builder
public record PageLessonDto(
        Long lessonId,
        String title,
        String description,
        String imageUrl
) {

}