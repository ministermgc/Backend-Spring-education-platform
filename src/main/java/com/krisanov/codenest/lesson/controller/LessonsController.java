package com.krisanov.codenest.lesson.controller;

import com.krisanov.codenest.lesson.dto.LessonPageResponse;
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

import java.util.List;

@Tag(name = "Lesson page")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/lessons")
public class LessonsController {

    private final LessonService lessonService;

    @Operation(
            description = "Getting a lessons.",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            content = @Content(
                                    schema = @Schema(implementation = LessonPageResponse.class)
                            )
                    )
            }
    )
    @GetMapping(params = {"page", "size"})
    public Page<LessonPageResponse> getLessonPage(@ParameterObject Pageable pageable) {
        return lessonService.getLessonPage(pageable);
    }

    @GetMapping
    public List<LessonPageResponse> getLessonList() {
        return lessonService.findAll();
    }
}
