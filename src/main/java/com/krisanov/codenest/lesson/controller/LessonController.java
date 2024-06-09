package com.krisanov.codenest.lesson.controller;

import com.krisanov.codenest.common.dto.ResponseErrorDto;
import com.krisanov.codenest.lesson.dto.LessonRequest;
import com.krisanov.codenest.lesson.dto.LessonResponse;
import com.krisanov.codenest.lesson.service.LessonService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
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

@Tag(name = "Lesson management")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/lesson")
public class LessonController {

    private final LessonService lessonService;

    @Operation(
            description = "Getting a lesson",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            content = @Content(
                                    schema = @Schema(implementation = LessonResponse.class)
                            )
                    ),
                    @ApiResponse(
                            responseCode = "401",
                            description = "The user must be authorized to use.",
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
            }
    )
    @GetMapping("/{lessonId:\\d+}")
    public LessonResponse getLessonById(@PathVariable Long lessonId) {
        return lessonService.findById(lessonId);
    }

    @Operation(
            description = "Creating a lesson",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            content = @Content(
                                    schema = @Schema(implementation = LessonResponse.class)
                            )
                    ),
                    @ApiResponse(
                            responseCode = "401",
                            description = "The user must be authorized to use.",
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
                    )
            },
            security = @SecurityRequirement(name = "Bearer JWT")
    )
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public LessonResponse createLesson(@RequestBody @Valid LessonRequest lessonRequest) {
        return lessonService.save(lessonRequest);
    }

    @Operation(
            description = "Editing a lesson",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            content = @Content(
                                    schema = @Schema(implementation = LessonResponse.class)
                            )
                    ),
                    @ApiResponse(
                            responseCode = "401",
                            description = "The user must be authorized to use.",
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
                    )
            },
            security = @SecurityRequirement(name = "Bearer JWT")
    )
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{lessonId:\\d+}")
    public LessonResponse updateLessonById(@PathVariable Long lessonId,
                                           @RequestBody @Valid LessonRequest lessonRequest) {
        return lessonService.updateById(lessonId, lessonRequest);
    }

    @Operation(
            description = "Deleting a lesson",
            responses = {
                    @ApiResponse(
                            responseCode = "204"
                    ),
                    @ApiResponse(
                            responseCode = "401",
                            description = "The user must be authorized to use.",
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
                    )
            },
            security = @SecurityRequirement(name = "Bearer JWT")
    )
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{lessonId:\\d+}")
    public ResponseEntity<?> deleteLessonById(@PathVariable Long lessonId) {
        lessonService.deleteById(lessonId);
        return ResponseEntity
                .status(HttpStatus.NO_CONTENT)
                .build();
    }
}
