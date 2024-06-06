package com.krisanov.codenest.lesson.service;

import com.krisanov.codenest.lesson.dto.LessonPageResponse;
import com.krisanov.codenest.lesson.dto.LessonRequest;
import com.krisanov.codenest.lesson.dto.LessonResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface LessonService {

    /**
     * Saves a Lesson based on the given DTO object.
     *
     * @param lessonRequest the DTO containing the data to create a Lesson
     * @return the created Lesson as a DTO
     */
    LessonResponse save(LessonRequest lessonRequest);

    /**
     * Updates a Lesson with the given id using the data from the provided DTO object.
     *
     * @param lessonId the id of the Lesson to update
     * @param lessonRequest the DTO containing the data for update
     * @return the updated Lesson as a DTO
     */
    LessonResponse updateById(Long lessonId, LessonRequest lessonRequest);

    /**
     * Deletes the Lesson with the given id.
     *
     * @param lessonId the id of the Lesson to delete
     */
    void deleteById(Long lessonId);

    /**
     * Finds the Lesson with the given id.
     *
     * @param lessonId the id of the Lesson to find
     * @return the found Lesson as a DTO
     */
    LessonResponse findById(Long lessonId);

    /**
     * Gets a paginated feed of Lessons.
     *
     * @param pageable the Pageable object containing pagination info
     * @return a Page containing Lessons as DTOs
     */
    Page<LessonPageResponse> getLessonFeed(Pageable pageable);

    /**
     * Gets a paginated list of Lessons.
     *
     * @param pageable the Pageable object containing pagination info
     * @return a Page containing Lessons as DTOs
     */
    Page<LessonPageResponse> getLessonPage(Pageable pageable);

    List<LessonPageResponse> findAll();
}
