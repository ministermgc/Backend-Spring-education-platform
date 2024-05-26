package com.krisanov.codenest.lesson.dto;

import lombok.Builder;

import java.util.List;

@Builder
public record LessonDto(
        Long lessonId,
        String title,
        List<ParagraphDto> paragraphs,
        List<CodeFragmentDto> codeFragments
) {

}
