package com.krisanov.codenest.lesson.dto;

import com.krisanov.codenest.lesson.domain.CodeLanguage;
import lombok.Builder;

@Builder
public record CodeFragmentResponseDto(
        Long codeFragmentId,
        String description,
        String code,
        CodeLanguage codeLanguage
) {

}
