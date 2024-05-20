package com.krisanov.codenest.lesson.controller;

import com.krisanov.codenest.lesson.dto.PageLessonResponseDto;
import com.krisanov.codenest.lesson.service.LessonService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controller for handling requests related to multiple Lesson resources.
 *
 * <p>
 * This controller manages HTTP request mappings related to operations to retrieve a batch of
 * Lesson resources. This includes fetching a paginated feed and fetching all lessons in a paginated format.
 * </p>
 *
 * @author Maxim Krisanov
 * @see com.krisanov.codenest.lesson.service.LessonService
 * @see com.krisanov.codenest.lesson.dto.PageLessonResponseDto
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/lessons")
public class LessonsController {

    /**
     * Service that contains business logic for handling Lesson information.
     */
    private final LessonService lessonService;

    /**
     * Fetches a paginated feed of Lessons.
     *
     * @param pageable defines the pagination parameters
     * @return a page containing instances of PageLessonResponseDto
     */
    @GetMapping("/feed")
    public Page<PageLessonResponseDto> getFeed(Pageable pageable) {
        return lessonService.findFeed(pageable);
    }

    /**
     * Retrieves all Lessons in a paginated format.
     *
     * @param pageable defines the pagination parameters
     * @return a page containing instances of PageLessonResponseDto
     */
    @GetMapping
    public Page<PageLessonResponseDto> getAllLessons(Pageable pageable) {
        return lessonService.findAll(pageable);
    }
}
