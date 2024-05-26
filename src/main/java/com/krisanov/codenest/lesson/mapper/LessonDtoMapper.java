package com.krisanov.codenest.lesson.mapper;

import com.krisanov.codenest.lesson.domain.Lesson;
import com.krisanov.codenest.lesson.dto.LessonDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {CodeFragmentDtoMapper.class, ParagraphDtoMapper.class})
public interface LessonDtoMapper {

    /**
     * Converts a Lesson object into a LessonDto object, mapping fields according to the defined specifications.
     *
     * @param lesson The Lesson entity object to be transformed into a LessonDto object.
     * @return A new LessonDto object containing data copied from the Lesson entity object.
     */
    @Mapping(target = "lessonId", source = "id")
    LessonDto toDto(Lesson lesson);
}