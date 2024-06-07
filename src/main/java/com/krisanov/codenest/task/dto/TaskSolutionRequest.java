package com.krisanov.codenest.task.dto;

import jakarta.validation.constraints.NotNull;

public record TaskSolutionRequest(@NotNull String userTaskAnswer) {

}
