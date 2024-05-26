package com.krisanov.codenest.lesson.mapper;

import com.krisanov.codenest.lesson.domain.Paragraph;
import com.krisanov.codenest.lesson.dto.ParagraphDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ParagraphDtoMapper {

    /**
     * Converts a Paragraph object into a ParagraphDto object, mapping fields accordingly.
     *
     * @param paragraph The Paragraph object to be converted into a ParagraphDto object.
     * @return A new ParagraphDto object containing data copied from the Paragraph object.
     */
    @Mapping(target = "paragraphId", source = "id")
    ParagraphDto toDto(Paragraph paragraph);
}
