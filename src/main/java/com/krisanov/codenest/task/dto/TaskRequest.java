package com.krisanov.codenest.task.dto;

public record TaskRequest(String title, String text, String answer, Long lessonId) {
}
