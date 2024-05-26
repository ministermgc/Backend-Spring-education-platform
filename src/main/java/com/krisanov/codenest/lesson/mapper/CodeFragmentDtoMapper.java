package com.krisanov.codenest.lesson.mapper;

import com.krisanov.codenest.lesson.domain.CodeFragment;
import com.krisanov.codenest.lesson.dto.CodeFragmentDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CodeFragmentDtoMapper {

    /**
     * Converts a CodeFragment object into a CodeFragmentDto object, mapping fields as required.
     *
     * @param codeFragment The CodeFragment object to be transformed into a CodeFragmentDto object.
     * @return A new CodeFragmentDto object containing data transferred from the CodeFragment object.
     */
    @Mapping(target = "codeFragmentId", source = "id")
    CodeFragmentDto toDto(CodeFragment codeFragment);
}