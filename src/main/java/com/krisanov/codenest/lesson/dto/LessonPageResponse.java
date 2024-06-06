package com.krisanov.codenest.lesson.dto;

public record LessonPageResponse(
        Long lessonId,
        String title,
        String description,
        String imageUrl
) {

}
