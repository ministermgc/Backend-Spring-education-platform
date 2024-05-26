package com.krisanov.codenest.lesson.controller;

import com.krisanov.codenest.common.dto.ResponseErrorDto;
import com.krisanov.codenest.lesson.dto.LessonDto;
import com.krisanov.codenest.lesson.dto.PageLessonDto;
import com.krisanov.codenest.lesson.service.LessonService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Lessons provided to the user")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/lessons")
public class LessonsController {

    private final LessonService lessonService;

    @Operation(
            description = "Lessons presented as recommendations to the user.",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            content = @Content(
                                    schema = @Schema(implementation = PageLessonDto.class)
                            )
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "In case the lessons are not found.",
                            content = @Content(
                                    schema = @Schema(implementation = ResponseErrorDto.class)
                            )
                    ),
            }
    )
    @GetMapping("/feed")
    public Page<PageLessonDto> getFeed(@ParameterObject Pageable pageable) {
        return lessonService.findFeed(pageable);
    }

    @Operation(
            description = "Full list of lessons.",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            content = @Content(
                                    schema = @Schema(implementation = LessonDto.class)
                            )
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "In case the lessons are not found.",
                            content = @Content(
                                    schema = @Schema(implementation = ResponseErrorDto.class)
                            )
                    ),
            }
    )
    @GetMapping
    public Page<PageLessonDto> getAllLessons(@ParameterObject Pageable pageable) {
        return lessonService.findAll(pageable);
    }
}
