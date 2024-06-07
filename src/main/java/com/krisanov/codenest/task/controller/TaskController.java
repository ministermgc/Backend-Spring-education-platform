package com.krisanov.codenest.task.controller;

import com.krisanov.codenest.common.dto.ResponseErrorDto;
import com.krisanov.codenest.task.dto.TaskRequest;
import com.krisanov.codenest.task.dto.TaskResponse;
import com.krisanov.codenest.task.service.TaskService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Task management")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/task")
public class TaskController {

    private final TaskService taskService;

    @Operation(
            description = "Getting task.",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            content = @Content(
                                    schema = @Schema(implementation = TaskResponse.class)
                            )
                    ),
                    @ApiResponse(
                            responseCode = "401",
                            description = "The user must be logged in to perform this action.",
                            content = @Content(
                                    schema = @Schema(implementation = ResponseErrorDto.class)
                            )
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "In case when the task is not found.",
                            content = @Content(
                                    schema = @Schema(implementation = ResponseErrorDto.class)
                            )
                    )
            },
            security = @SecurityRequirement(name = "Bearer JWT")
    )
    @GetMapping("/{taskId:\\d+}")
    public TaskResponse getTaskById(@PathVariable Long taskId) {
        return taskService.findById(taskId);
    }

    @Operation(
            description = "Creating task.",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            content = @Content(
                                    schema = @Schema(implementation = TaskResponse.class)
                            )
                    ),
                    @ApiResponse(
                            responseCode = "401",
                            description = "The user must be logged in to perform this action.",
                            content = @Content(
                                    schema = @Schema(implementation = ResponseErrorDto.class)
                            )
                    ),
                    @ApiResponse(
                            responseCode = "403",
                            description = "The user must be an administrator to perform this action.",
                            content = @Content(
                                    schema = @Schema(implementation = ResponseErrorDto.class)
                            )
                    )
            },
            security = @SecurityRequirement(name = "Bearer JWT")
    )
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public TaskResponse createTask(@RequestBody TaskRequest taskRequest) {
        return taskService.save(taskRequest);
    }

    @Operation(
            description = "Updating task.",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            content = @Content(
                                    schema = @Schema(implementation = TaskResponse.class)
                            )
                    ),
                    @ApiResponse(
                            responseCode = "401",
                            description = "The user must be logged in to perform this action.",
                            content = @Content(
                                    schema = @Schema(implementation = ResponseErrorDto.class)
                            )
                    ),
                    @ApiResponse(
                            responseCode = "403",
                            description = "The user must be an administrator to perform this action.",
                            content = @Content(
                                    schema = @Schema(implementation = ResponseErrorDto.class)
                            )
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "In case when the task is not found.",
                            content = @Content(
                                    schema = @Schema(implementation = ResponseErrorDto.class)
                            )
                    )
            },
            security = @SecurityRequirement(name = "Bearer JWT")
    )
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{taskId:\\d+}")
    public TaskResponse updateTaskById(@PathVariable Long taskId,
                                       @RequestBody TaskRequest taskRequest) {
        return taskService.updateById(taskId, taskRequest);
    }

    @Operation(
            description = "Deleting task.",
            responses = {
                    @ApiResponse(
                            responseCode = "204",
                            content = @Content(
                                    schema = @Schema(implementation = ResponseEntity.class)
                            )
                    ),
                    @ApiResponse(
                            responseCode = "401",
                            description = "The user must be logged in to perform this action.",
                            content = @Content(
                                    schema = @Schema(implementation = ResponseErrorDto.class)
                            )
                    ),
                    @ApiResponse(
                            responseCode = "403",
                            description = "The user must be an administrator to perform this action.",
                            content = @Content(
                                    schema = @Schema(implementation = ResponseErrorDto.class)
                            )
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "In case when the task is not found.",
                            content = @Content(
                                    schema = @Schema(implementation = ResponseErrorDto.class)
                            )
                    )
            },
            security = @SecurityRequirement(name = "Bearer JWT")
    )
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{taskId:\\d+}")
    public ResponseEntity<?> deleteTaskById(@PathVariable Long taskId) {
        taskService.deleteById(taskId);
        return ResponseEntity
                .status(HttpStatus.NO_CONTENT)
                .build();
    }
}
