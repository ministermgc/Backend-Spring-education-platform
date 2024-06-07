package com.krisanov.codenest.lesson.mapper;

import com.krisanov.codenest.domain.Lesson;
import com.krisanov.codenest.lesson.dto.LessonRequest;
import com.krisanov.codenest.util.mapper.ImageRequestMapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring", uses = {ImageRequestMapper.class, LessonContentRequestMapper.class})
public interface LessonRequestMapper {

    /**
     * Mapping a LessonRequest DTO to a Lesson entity.
     *
     * @param lessonRequest the DTO object to be mapped to a Lesson entity
     * @return a Lesson entity
     */
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "tasks", ignore = true)
    @Mapping(target = "imageUrl", source = "image", qualifiedByName = "toImageUrl")
    Lesson toEntity(LessonRequest lessonRequest);

    /**
     * Updates a Lesson entity from a LessonRequest DTO.
     *
     * @param lessonRequest the DTO object having the data to update a Lesson entity
     * @param lesson the entity object to be updated
     * @return an updated Lesson entity
     */
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "tasks", ignore = true)
    @Mapping(target = "imageUrl", source = "image", qualifiedByName = "toImageUrl")
    Lesson update(LessonRequest lessonRequest, @MappingTarget Lesson lesson);
}
