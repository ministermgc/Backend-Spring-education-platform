package com.krisanov.codenest.lesson.mapper;

import com.krisanov.codenest.lesson.domain.Lesson;
import com.krisanov.codenest.lesson.dto.LessonResponseDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

/**
 * Mapper interface for converting Lesson domain entity to
 * LessonResponseDto data transfer object.
 *
 * <p>
 * This mapper uses <a href="https://mapstruct.org/">MapStruct</a>,
 * a code generator that greatly simplifies the task of creating type-safe
 * and performant mappers for Java bean classes. This mapper is made available as
 * a Spring bean in the application context.
 * </p>
 *
 * <p>
 * It uses {@link com.krisanov.codenest.lesson.mapper.CodeFragmentResponseDtoMapper}
 * for mapping from the {@link com.krisanov.codenest.lesson.domain.CodeFragment}
 * domain entity to the {@link com.krisanov.codenest.lesson.dto.CodeFragmentResponseDto}
 * data transfer object.
 * </p>
 *
 * @author Maxim Krisanov
 * @see com.krisanov.codenest.lesson.domain.Lesson
 * @see com.krisanov.codenest.lesson.dto.LessonResponseDto
 * @see com.krisanov.codenest.lesson.mapper.CodeFragmentResponseDtoMapper
 */
@Mapper(componentModel = "spring", uses = CodeFragmentResponseDtoMapper.class)
public interface LessonResponseDtoMapper {

    /**
     * Maps a given {@link com.krisanov.codenest.lesson.domain.Lesson} instance to a
     * {@link com.krisanov.codenest.lesson.dto.LessonResponseDto} instance.
     *
     * <p>
     * This method defined with a @Mapping annotation which allows for specific
     * fields in the source object to be mapped to fields in the target object.
     * Here, it maps the 'id' field in the Lesson entity to the 'lessonId' field
     * in the LessonResponseDto.
     * </p>
     *
     * @param lesson the source Lesson entity
     * @return the mapped LessonResponseDto
     */
    @Mapping(target = "lessonId", source = "id")
    LessonResponseDto toDto(Lesson lesson);
}