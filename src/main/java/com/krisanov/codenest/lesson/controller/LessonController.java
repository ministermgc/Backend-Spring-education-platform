package com.krisanov.codenest.lesson.controller;

import com.krisanov.codenest.common.dto.ResponseErrorDto;
import com.krisanov.codenest.lesson.dto.LessonDto;
import com.krisanov.codenest.lesson.service.LessonService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Lesson that the user learns")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/lesson")
public class LessonController {

    private final LessonService lessonService;

    @Operation(
            description = "User receiving a lesson.",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            content = @Content(
                                    schema = @Schema(implementation = LessonDto.class)
                            )
                    ),
                    @ApiResponse(
                            responseCode = "401",
                            description = "You must be logged in to perform this action.",
                            content = @Content(
                                    schema = @Schema(implementation = ResponseErrorDto.class)
                            )
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "In case the lesson is not found.",
                            content = @Content(
                                    schema = @Schema(implementation = ResponseErrorDto.class)
                            )
                    ),
            },
            security = @SecurityRequirement(name = "Bearer JWT")
    )
    @GetMapping("/{lessonId:\\d+}")
    public LessonDto getLessonById(@PathVariable Long lessonId) {
        return lessonService.findById(lessonId);
    }
}
