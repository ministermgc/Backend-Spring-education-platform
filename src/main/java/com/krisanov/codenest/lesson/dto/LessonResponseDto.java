package com.krisanov.codenest.lesson.dto;

import lombok.Builder;

@Builder
public record LessonResponseDto(
        Long lessonId,
        String title,
        String textContent,
        CodeFragmentResponseDto codeFragment
) {

}
