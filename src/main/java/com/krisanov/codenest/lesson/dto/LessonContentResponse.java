package com.krisanov.codenest.lesson.dto;

import lombok.Builder;

@Builder
public record LessonContentResponse(Long contentId, String paragraph, String code, String codeLanguage) {

}
