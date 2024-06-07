package com.krisanov.codenest.useraccount.mapper;

import com.krisanov.codenest.domain.UserAccount;
import com.krisanov.codenest.useraccount.dto.UpdateUserAccountRequestDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface UpdateUserAccountDtoToUserAccountMapper {


    @Mapping(target = "id", ignore = true)
    @Mapping(target = "username", ignore = true)
    @Mapping(target = "authorities", ignore = true)
    UserAccount updateUserAccount(UpdateUserAccountRequestDto updateUserAccountRequestDTO,
                                  @MappingTarget UserAccount userAccount);
}