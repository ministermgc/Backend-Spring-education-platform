package com.krisanov.codenest.lesson.mapper;

import com.krisanov.codenest.lesson.domain.Lesson;
import com.krisanov.codenest.lesson.dto.PageLessonResponseDto;
import com.krisanov.codenest.util.mapper.ImageResponseDtoMapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.data.domain.Page;

/**
 * Mapper interface for converting a Lesson domain entity to a
 * PageLessonResponseDto data transfer object as well as a Page<Lesson> to a Page<PageLessonResponseDto>.
 *
 * <p>
 * This mapper employs <a href="https://mapstruct.org/">MapStruct</a>,
 * a code generator tool that facilitates the mappings between different bean types.
 * This Mapper is registered as a Spring Bean in the application context.
 * </p>
 *
 * <p>
 * The mapper uses {@link ImageResponseDtoMapper}
 * for mapping from an image URL to an ImageResponseDTO.
 * </p>
 *
 * @author Maxim Krisanov
 * @see com.krisanov.codenest.lesson.domain.Lesson
 * @see com.krisanov.codenest.lesson.dto.PageLessonResponseDto
 * @see ImageResponseDtoMapper
 */
@Mapper(componentModel = "spring", uses = ImageResponseDtoMapper.class)
public interface PageLessonResponseDtoMapper {

    /**
     * Converts a given {@link com.krisanov.codenest.lesson.domain.Lesson} instance to a
     * {@link com.krisanov.codenest.lesson.dto.PageLessonResponseDto} instance.
     *
     * <p>
     * The method defined with @Mapping annotations, which enable mapping
     * specific fields in the source object to fields in the target object.
     * The 'id' field in the Lesson entity is mapped to the 'lessonId' field in the
     * PageLessonResponseDto. The method also converts an 'imageUrl' in the Lesson
     * entity to an ImageResponseDTO in the PageLessonResponseDto with the help of
     * ImageResponseDTOMapper.
     * </p>
     *
     * @param lesson the source Lesson entity
     * @return the corresponding PageLessonResponseDto
     */
    @Mapping(target = "lessonId", source = "id")
    @Mapping(target = "imageUrl", source = "imageUrl", qualifiedByName = "toImageResponse")
    PageLessonResponseDto toDto(Lesson lesson);

    /**
     * Converts a {@link org.springframework.data.domain.Page} of
     * {@link com.krisanov.codenest.lesson.domain.Lesson} objects to a Page of
     * {@link com.krisanov.codenest.lesson.dto.PageLessonResponseDto} objects.
     * <p>
     * This is accomplished by mapping each individual Lesson to a PageLessonResponseDto
     * using the toDto method defined above.
     *
     * @param lessons Page of Lesson entities
     * @return a Page of corresponding PageLessonResponseDto objects
     */
    default Page<PageLessonResponseDto> toDtoPage(Page<Lesson> lessons) {
        return lessons.map(this::toDto);
    }
}
