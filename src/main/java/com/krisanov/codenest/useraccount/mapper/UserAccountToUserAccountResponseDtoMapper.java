package com.krisanov.codenest.useraccount.mapper;

import com.krisanov.codenest.domain.UserAccount;
import com.krisanov.codenest.useraccount.dto.UserAccountResponseDto;
import org.mapstruct.Mapper;

/**
 * UserAccountToUserAccountResponseDtoMapper is an interface that provides the mapping
 * between UserAccount domain object and UserAccountResponseDto.
 *
 * @author Maxim Krisanov
 * @version 1.0 05/17/2024
 */
@Mapper(componentModel = "spring")
public interface UserAccountToUserAccountResponseDtoMapper {

    /**
     * Method to map from UserAccount to UserAccountResponseDto.
     *
     * @param userAccount the source object to map from
     * @return UserAccountResponseDto object
     */
    UserAccountResponseDto toDto(UserAccount userAccount);
}
