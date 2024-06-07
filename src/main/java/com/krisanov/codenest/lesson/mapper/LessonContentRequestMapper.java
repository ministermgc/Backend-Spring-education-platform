package com.krisanov.codenest.lesson.mapper;

import com.krisanov.codenest.domain.LessonContent;
import com.krisanov.codenest.lesson.dto.LessonContentRequest;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface LessonContentRequestMapper {

    /**
     * Mapping a LessonContentRequest DTO to a LessonContent entity.
     *
     * @param lessonContentRequest the DTO object to be mapped to a LessonContent entity
     * @return a LessonContent entity
     */
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "lesson", ignore = true)
    LessonContent toEntity(LessonContentRequest lessonContentRequest);
}
