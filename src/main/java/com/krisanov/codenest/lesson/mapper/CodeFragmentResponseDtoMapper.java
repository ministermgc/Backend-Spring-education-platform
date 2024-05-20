package com.krisanov.codenest.lesson.mapper;

import com.krisanov.codenest.lesson.domain.CodeFragment;
import com.krisanov.codenest.lesson.dto.CodeFragmentResponseDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

/**
 * Mapper interface for converting CodeFragment domain entity to
 * CodeFragmentResponseDto data transfer object.
 *
 * <p>
 * This mapper uses <a href="https://mapstruct.org/">MapStruct</a>,
 * a code generator that simplifies the mappings between different bean types.
 * This mapper is made available as a Spring bean in the application context.
 * </p>
 *
 * @author Maxim Krisanov
 * @see com.krisanov.codenest.lesson.domain.CodeFragment
 * @see com.krisanov.codenest.lesson.dto.CodeFragmentResponseDto
 */
@Mapper(componentModel = "spring")
public interface CodeFragmentResponseDtoMapper {

    /**
     * Maps a given {@link com.krisanov.codenest.lesson.domain.CodeFragment} instance to a
     * {@link com.krisanov.codenest.lesson.dto.CodeFragmentResponseDto} instance.
     *
     * <p>
     * This method defined with @Mapping annotation which allows us to map
     * specific fields in the source object to fields in the target object.
     * In this case it is mapping the 'id' in the CodeFragment entity to the
     * 'codeFragmentId' in the CodeFragmentResponseDto.
     * </p>
     *
     * @param codeFragment the source CodeFragment entity
     * @return the mapped CodeFragmentResponseDto
     */
    @Mapping(target = "codeFragmentId", source = "id")
    CodeFragmentResponseDto toDto(CodeFragment codeFragment);
}