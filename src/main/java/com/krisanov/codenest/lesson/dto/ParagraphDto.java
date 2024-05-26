package com.krisanov.codenest.lesson.dto;

import lombok.Builder;

@Builder
public record ParagraphDto(
        Long paragraphId,
        String paragraphText,
        Integer paragraphPosition
) {
}
