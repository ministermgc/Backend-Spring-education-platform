package com.krisanov.codenest.task.dto;

import com.krisanov.codenest.task.domain.SolutionStatus;
import lombok.Builder;

@Builder
public record TaskSolutionResponse(SolutionStatus solutionStatus) {

}
