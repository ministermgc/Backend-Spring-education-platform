package com.krisanov.codenest.lesson.service.impl;

import com.krisanov.codenest.common.exception.NotFoundException;
import com.krisanov.codenest.lesson.dto.LessonDto;
import com.krisanov.codenest.lesson.dto.PageLessonDto;
import com.krisanov.codenest.lesson.mapper.LessonDtoMapper;
import com.krisanov.codenest.lesson.mapper.PageLessonDtoMapper;
import com.krisanov.codenest.lesson.repository.LessonRepository;
import com.krisanov.codenest.lesson.service.LessonService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

/**
 * Implementation of the {@link com.krisanov.codenest.lesson.service.LessonService}.
 */
@Service
@RequiredArgsConstructor
public class LessonServiceImpl implements LessonService {

    /**
     * The repository used for data retrieval, interacts with the database to fetch Lesson entities.
     */
    private final LessonRepository lessonRepository;

    /**
     * Used to convert a Page of Lesson entities to a corresponding Page of PageLessonDto objects.
     */
    private final PageLessonDtoMapper pageLessonDtoMapper;

    /**
     * Converts a Lesson entity to its corresponding LessonDto object.
     */
    private final LessonDtoMapper lessonDtoMapper;

    /**
     * {@inheritDoc}
     * <p>
     * Finds a paginated feed of Lessons and maps them to PageLessonDto objects.
     */
    @Override
    public Page<PageLessonDto> findFeed(Pageable pageable) {
        return pageLessonDtoMapper.toDtoPage(lessonRepository.findFeed(pageable));
    }

    /**
     * {@inheritDoc}
     * <p>
     * Retrieves all Lessons in a paginated format and maps them to PageLessonDto objects.
     */
    @Override
    public Page<PageLessonDto> findAll(Pageable pageable) {
        return pageLessonDtoMapper.toDtoPage(lessonRepository.findAll(pageable));
    }

    /**
     * {@inheritDoc}
     * <p>
     * Fetches a specific Lesson based on its id and maps it to a LessonDto object.
     * If the Lesson is not found, a NotFoundException is thrown.
     */
    @Override
    @Transactional
    public LessonDto findById(Long lessonId) {
        return lessonRepository.findById(lessonId)
                .map(lessonDtoMapper::toDto)
                .orElseThrow(() -> new NotFoundException(
                        "Lesson with %d id was not found".formatted(lessonId)));
    }
}
