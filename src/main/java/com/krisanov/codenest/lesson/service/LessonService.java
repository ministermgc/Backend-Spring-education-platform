package com.krisanov.codenest.lesson.service;

import com.krisanov.codenest.lesson.dto.LessonResponseDto;
import com.krisanov.codenest.lesson.dto.PageLessonResponseDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service interface that outlines the core operations related to Lessons.
 *
 * <p>
 * This interface defines methods for fetching, Paginated feed of lesson details,
 * retrieving all lessons in a paginated format and fetching a specific lesson
 * by its identifier.
 * </p>
 *
 * @author Maxim Krisanov
 * @see com.krisanov.codenest.lesson.dto.LessonResponseDto
 * @see com.krisanov.codenest.lesson.dto.PageLessonResponseDto
 */
public interface LessonService {

    /**
     * Retrieves a paginated feed of {@link com.krisanov.codenest.lesson.dto.PageLessonResponseDto} objects.
     *
     * @param pageable the pagination information
     * @return a Page containing PageLessonResponseDto objects
     */
    Page<PageLessonResponseDto> findFeed(Pageable pageable);

    /**
     * Retrieves a paginated list of all {@link com.krisanov.codenest.lesson.dto.PageLessonResponseDto} objects.
     *
     * @param pageable the pagination information
     * @return a Page containing PageLessonResponseDto objects
     */
    Page<PageLessonResponseDto> findAll(Pageable pageable);

    /**
     * Retrieves a specific {@link com.krisanov.codenest.lesson.dto.LessonResponseDto} identified by the provided lessonId.
     *
     * @param lessonId the id of the lesson to be retrieved
     * @return the corresponding LessonResponseDto
     */
    LessonResponseDto findById(Long lessonId);
}
