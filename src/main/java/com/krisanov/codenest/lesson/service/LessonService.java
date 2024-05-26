package com.krisanov.codenest.lesson.service;

import com.krisanov.codenest.lesson.dto.LessonDto;
import com.krisanov.codenest.lesson.dto.PageLessonDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface LessonService {

    /**
     * Retrieves a page of PageLessonDto objects for the feed, wrapped as a Page object.
     *
     * @param pageable A Pageable object specifying information about the desired page and page size.
     * @return A Page object containing PageLessonDto objects for the requested page of the feed.
     */
    Page<PageLessonDto> findFeed(Pageable pageable);

    /**
     * Retrieves all PageLessonDto objects, wrapped as a Page object.
     *
     * @param pageable A Pageable object specifying information about the desired page and page size.
     * @return A Page object containing all PageLessonDto objects for the requested page.
     */
    Page<PageLessonDto> findAll(Pageable pageable);

    /**
     * Finds a lesson by its id, represented as a LessonDto object.
     *
     * @param lessonId The identifier of the lesson to be retrieved.
     * @return The lesson details represented in a LessonDto object.
     */
    LessonDto findById(Long lessonId);
}
