package com.krisanov.codenest.lesson.controller;

import com.krisanov.codenest.lesson.dto.LessonResponseDto;
import com.krisanov.codenest.lesson.service.LessonService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * The controller for the Lesson resource.
 *
 * <p>
 * This controller manages the HTTP request mappings for the Lesson resource. It particularly supports
 * HTTP GET request to fetch a specific Lesson by its identifier. The returned object is a DTO representation
 * of the Lesson.
 * </p>
 *
 * @author Maxim Krisanov
 * @see com.krisanov.codenest.lesson.dto.LessonResponseDto
 * @see com.krisanov.codenest.lesson.service.LessonService
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/lesson")
public class LessonController {

    /**
     * Service that contains business logic for handling Lesson information.
     */
    private final LessonService lessonService;

    /**
     * Retrieves a single Lesson with specified identifier.
     *
     * @param lessonId the unique identifier of the Lesson to retrieve
     * @return LessonResponseDto representation of the Lesson
     */
    @GetMapping("/{lessonId:\\d+}")
    public LessonResponseDto getLessonById(@PathVariable Long lessonId) {
        return lessonService.findById(lessonId);
    }
}
