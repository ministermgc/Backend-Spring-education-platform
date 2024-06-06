package com.krisanov.codenest.lesson.mapper;

import com.krisanov.codenest.domain.LessonContent;
import com.krisanov.codenest.lesson.dto.LessonContentResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface LessonContentResponseMapper {

    /**
     * Mapping a LessonContent entity to a LessonContentResponse DTO.
     *
     * @param lessonContent the entity object to be mapped to a LessonContentResponse DTO
     * @return a LessonContentResponse DTO
     */
    @Mapping(target = "contentId", source = "id")
    LessonContentResponse toDto(LessonContent lessonContent);
}
