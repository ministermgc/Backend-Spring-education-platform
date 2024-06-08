package com.krisanov.codenest.lesson.service.impl;

import com.krisanov.codenest.common.exception.NotFoundException;
import com.krisanov.codenest.domain.Lesson;
import com.krisanov.codenest.lesson.dto.LessonPageResponse;
import com.krisanov.codenest.lesson.dto.LessonRequest;
import com.krisanov.codenest.lesson.dto.LessonResponse;
import com.krisanov.codenest.lesson.mapper.LessonPageResponseMapper;
import com.krisanov.codenest.lesson.mapper.LessonRequestMapper;
import com.krisanov.codenest.lesson.mapper.LessonResponseMapper;
import com.krisanov.codenest.lesson.service.LessonService;
import com.krisanov.codenest.repository.LessonRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * The LessonServiceImpl class provides a service layer for managing lessons.
 * It offers capabilities for saving, updating, deleting, and retrieving lessons.
 */
@Service
@RequiredArgsConstructor
public class LessonServiceImpl implements LessonService {

    /**
     * @see LessonRepository
     */
    private final LessonRepository lessonRepository;

    /**
     * @see LessonRequestMapper
     */
    private final LessonRequestMapper lessonRequestMapper;

    /**
     * @see LessonResponseMapper
     */
    private final LessonResponseMapper lessonResponseMapper;

    /**
     * @see LessonPageResponseMapper
     */
    private final LessonPageResponseMapper lessonPageResponseMapper;

    /**
     * Save a lesson
     *
     * @param lessonRequest the request object containing data to create a lesson
     * @return the lessonResponse that was persisted in the database
     */
    @Override
    @Transactional
    public LessonResponse save(LessonRequest lessonRequest) {
        Lesson lesson = lessonRequestMapper.toEntity(lessonRequest);

        lesson.getContent()
                .forEach(contentItem -> contentItem.setLesson(lesson));

        return lessonResponseMapper.toDto(lessonRepository.save(lesson));
    }

    /**
     * Update an existing lesson
     *
     * @param lessonId      the identifier of the lesson to be updated
     * @param lessonRequest the request object containing the new lesson data
     * @return the updated lesson
     * @throws NotFoundException if no lesson with the given identifier was found
     */
    @Override
    @Transactional
    public LessonResponse updateById(Long lessonId, LessonRequest lessonRequest) {
        return lessonRepository
                .findById(lessonId)
                .map(lesson -> {
                    lessonRequestMapper.update(lessonRequest, lesson);
                    lesson.getContent().forEach(content -> content.setLesson(lesson));
                    Lesson savedLesson = lessonRepository.save(lesson);
                    return lessonResponseMapper.toDto(savedLesson);
                })
                .orElseThrow(() -> new NotFoundException(
                        "Lesson with id %d is not found".formatted(lessonId)));
    }

    /**
     * Deletes a lesson by identifier.
     *
     * @param lessonId Identifier of the lesson to delete.
     */
    @Override
    public void deleteById(Long lessonId) {
        lessonRepository.deleteById(lessonId);
    }

    /**
     * Retrieves a lesson by identifier.
     *
     * @param lessonId ID of the lesson
     * @return LessonResponse of the lesson
     * @throws NotFoundException if no lesson with the given identifier was found
     */
    @Override
    @Transactional
    public LessonResponse findById(Long lessonId) {
        return lessonRepository
                .findById(lessonId)
                .map(lessonResponseMapper::toDto)
                .orElseThrow(() -> new NotFoundException(
                        "Lesson with id %d is not found".formatted(lessonId)));
    }

    /**
     * Retrieves a paginated list of lessons.
     *
     * @param pageable object containing details about pagination and sorting
     * @return a paginated list of lessons
     */
    @Override
    public Page<LessonPageResponse> getLessonPage(Pageable pageable) {
        return lessonRepository
                .findAll(pageable)
                .map(lessonPageResponseMapper::toDto);
    }

    @Override
    public List<LessonPageResponse> findAll() {
        return lessonRepository
                .findAll()
                .stream()
                .map(lessonPageResponseMapper::toDto)
                .toList();
    }
}
