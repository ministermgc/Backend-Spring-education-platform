package com.krisanov.codenest.task.controller;

import com.krisanov.codenest.common.dto.ResponseErrorDto;
import com.krisanov.codenest.task.dto.PageTaskResponse;
import com.krisanov.codenest.task.service.TaskService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Getting tasks")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/tasks")
public class TasksController {

    private final TaskService taskService;

    @Operation(
            description = "Getting a paginated task list for the specified lesson.",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            content = @Content(
                                    schema = @Schema(implementation = Page.class)
                            )
                    ),
                    @ApiResponse(
                            responseCode = "401",
                            description = "The user must be logged in to perform this action.",
                            content = @Content(
                                    schema = @Schema(implementation = ResponseErrorDto.class)
                            )
                    ),
            }
    )
    @GetMapping("/{lessonId:\\d+}")
    public Page<PageTaskResponse> getAllTasksByLessonId(@PathVariable Long lessonId, Pageable pageable) {
        return taskService.findAllByLessonId(lessonId, pageable);
    }

    @Operation(
            description = "Getting a paginated task list.",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            content = @Content(
                                    schema = @Schema(implementation = Page.class)
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
                            description = "The user must be an administrator to use.",
                            content = @Content(
                                    schema = @Schema(implementation = ResponseErrorDto.class)
                            )
                    ),
            }
    )
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping(params = {"page", "size"})
    public Page<PageTaskResponse> getAllTasks(Pageable pageable) {
        return taskService.findAll(pageable);
    }
}
