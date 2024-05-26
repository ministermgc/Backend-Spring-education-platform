package com.krisanov.codenest.lesson.mapper;

import com.krisanov.codenest.lesson.domain.Lesson;
import com.krisanov.codenest.lesson.dto.PageLessonDto;
import com.krisanov.codenest.util.mapper.ImageResponseDtoMapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.data.domain.Page;

@Mapper(componentModel = "spring", uses = ImageResponseDtoMapper.class)
public interface PageLessonDtoMapper {

    /**
     * Transforms a Lesson object into a PageLessonDto object, with specified field mappings.
     *
     * @param lesson The Lesson object to be converted into a PageLessonDto object.
     * @return A new PageLessonDto object containing information from the Lesson object.
     */
    @Mapping(target = "lessonId", source = "id")
    @Mapping(target = "imageUrl", source = "imageUrl", qualifiedByName = "toImageResponse")
    PageLessonDto toDto(Lesson lesson);

    /**
     * Converts a Page of Lesson objects into a Page of PageLessonDto objects.
     *
     * @param lessons A Page of Lesson objects.
     * @return A new Page of PageLessonDto objects, each one mapped from a corresponding Lesson object.
     */
    default Page<PageLessonDto> toDtoPage(Page<Lesson> lessons) {
        return lessons.map(this::toDto);
    }
}
