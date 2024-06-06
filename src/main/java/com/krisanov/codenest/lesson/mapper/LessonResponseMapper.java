package com.krisanov.codenest.lesson.mapper;

import com.krisanov.codenest.domain.Lesson;
import com.krisanov.codenest.lesson.dto.LessonResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = LessonContentResponseMapper.class)
public interface LessonResponseMapper {

    /**
     * Mapping a Lesson entity to a LessonResponse DTO.
     *
     * @param lesson the entity object to be mapped to a LessonResponse DTO
     * @return a LessonResponse DTO
     */
    @Mapping(target = "lessonId", source = "id")
    LessonResponse toDto(Lesson lesson);
}
