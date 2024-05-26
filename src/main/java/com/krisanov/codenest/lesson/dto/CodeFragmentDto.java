package com.krisanov.codenest.lesson.dto;

import lombok.Builder;

@Builder
public record CodeFragmentDto(
        Long codeFragmentId,
        String code,
        Integer codeFragmentPosition
) {

}
