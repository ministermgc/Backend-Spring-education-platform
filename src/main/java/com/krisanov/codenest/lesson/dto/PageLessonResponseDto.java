package com.krisanov.codenest.lesson.dto;

import lombok.Builder;

@Builder
public record PageLessonResponseDto(
        Long lessonId,
        String title,
        String description,
        String imageUrl
) {

}