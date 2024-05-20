package com.krisanov.codenest.lesson.service.impl;

import com.krisanov.codenest.common.exception.NotFoundException;
import com.krisanov.codenest.lesson.dto.LessonResponseDto;
import com.krisanov.codenest.lesson.dto.PageLessonResponseDto;
import com.krisanov.codenest.lesson.mapper.LessonResponseDtoMapper;
import com.krisanov.codenest.lesson.mapper.PageLessonResponseDtoMapper;
import com.krisanov.codenest.lesson.repository.LessonRepository;
import com.krisanov.codenest.lesson.service.LessonService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

/**
 * Implementation of the {@link com.krisanov.codenest.lesson.service.LessonService}.
 *
 * <p>
 * This service offers operations related to Lessons. It fetches paginated feed of lesson details,
 * retrieves all lessons in a paginated format and fetches a specific lesson by its identifier.
 * The repository layer is engaged for the data retrieval, and fetched data is then
 * converted to DTOs using mappers.
 * </p>
 *
 * @author Maxim Krisanov
 * @see com.krisanov.codenest.lesson.dto.LessonResponseDto
 * @see com.krisanov.codenest.lesson.dto.PageLessonResponseDto
 * @see com.krisanov.codenest.lesson.repository.LessonRepository
 * @see com.krisanov.codenest.lesson.mapper.LessonResponseDtoMapper
 * @see com.krisanov.codenest.lesson.mapper.PageLessonResponseDtoMapper
 */
@Service
@RequiredArgsConstructor
public class LessonServiceImpl implements LessonService {

    /**
     * The repository used for data retrieval, interacts with the database to fetch Lesson entities.
     */
    private final LessonRepository lessonRepository;

    /**
     * Used to convert a Page of Lesson entities to a corresponding Page of PageLessonResponseDto objects.
     */
    private final PageLessonResponseDtoMapper pageLessonResponseDtoMapper;

    /**
     * Converts a Lesson entity to its corresponding LessonResponseDto object.
     */
    private final LessonResponseDtoMapper lessonResponseDtoMapper;

    /**
     * {@inheritDoc}
     * <p>
     * Finds a paginated feed of Lessons and maps them to PageLessonResponseDto objects.
     */
    @Override
    public Page<PageLessonResponseDto> findFeed(Pageable pageable) {
        return pageLessonResponseDtoMapper.toDtoPage(lessonRepository.findFeed(pageable));
    }

    /**
     * {@inheritDoc}
     * <p>
     * Retrieves all Lessons in a paginated format and maps them to PageLessonResponseDto objects.
     */
    @Override
    public Page<PageLessonResponseDto> findAll(Pageable pageable) {
        return pageLessonResponseDtoMapper.toDtoPage(lessonRepository.findAll(pageable));
    }

    /**
     * {@inheritDoc}
     * <p>
     * Fetches a specific Lesson based on its id and maps it to a LessonResponseDto object.
     * If the Lesson is not found, a NotFoundException is thrown.
     */
    @Override
    public LessonResponseDto findById(Long lessonId) {
        return lessonRepository.findById(lessonId)
                .map(lessonResponseDtoMapper::toDto)
                .orElseThrow(() -> new NotFoundException(
                        "Lesson with %d id was not found".formatted(lessonId)));
    }
}
