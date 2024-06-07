package com.krisanov.codenest.authentication.mapper;

import com.krisanov.codenest.authentication.dto.RegistrationRequestDto;
import com.krisanov.codenest.domain.UserAccount;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

/**
 * Mapper interface for transforming RegistrationRequestDto into UserAccount domain object.
 * This is intended to provide strict typechecking for DTO to UserAccount transformation logic.
 * It uses the MapStruct framework under the hood for the actual transformation.
 */
@Mapper(componentModel = "spring")
public interface RegistrationRequestDtoMapper {

    /**
     * Transforms a RegistrationRequestDto object into a UserAccount domain object.
     * It maps the fields from the DTO to the UserAccount object.
     * Fields that are not meant to be mapped (like id and authorities) are explicitly ignored.
     *
     * @param registrationRequestDTO the registration request DTO object to transform.
     * @return a UserAccount domain object transformed from the DTO.
     */
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "authorities", ignore = true)
    UserAccount map(RegistrationRequestDto registrationRequestDTO);
}
