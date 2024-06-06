package com.krisanov.codenest.lesson.mapper;

import com.krisanov.codenest.domain.Lesson;
import com.krisanov.codenest.lesson.dto.LessonPageResponse;
import com.krisanov.codenest.util.mapper.ImageResponseDtoMapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = ImageResponseDtoMapper.class)
public interface LessonPageResponseMapper {

    /**
     * Mapping a Lesson entity to a LessonPageResponse DTO.
     *
     * @param lesson the entity object to be mapped to a LessonPageResponse DTO
     * @return a LessonPageResponse DTO
     */
    @Mapping(target = "lessonId", source = "id")
    @Mapping(target = "imageUrl", source = "imageUrl", qualifiedByName = "toImageResponse")
    LessonPageResponse toDto(Lesson lesson);
}
