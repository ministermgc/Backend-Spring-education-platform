package com.krisanov.codenest.useraccount.service.impl;

import com.krisanov.codenest.common.exception.NotFoundException;
import com.krisanov.codenest.domain.UserAccount;
import com.krisanov.codenest.repository.UserAccountRepository;
import com.krisanov.codenest.useraccount.dto.UpdateUserAccountRequestDto;
import com.krisanov.codenest.useraccount.dto.UserAccountResponseDto;
import com.krisanov.codenest.useraccount.mapper.UpdateUserAccountDtoToUserAccountMapper;
import com.krisanov.codenest.useraccount.mapper.UserAccountToUserAccountResponseDtoMapper;
import com.krisanov.codenest.useraccount.service.UserAccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserAccountServiceImpl implements UserAccountService {

    private final UserAccountRepository userAccountRepository;

    private final UpdateUserAccountDtoToUserAccountMapper updateUserAccountMapper;

    private final UserAccountToUserAccountResponseDtoMapper userAccountResponseMapper;

    private final PasswordEncoder passwordEncoder;

    @Override
    public UserAccountResponseDto findUserAccount() {
        String username = getUsernameFromUserDetails();
        return userAccountRepository
                .findByUsername(username)
                .map(userAccountResponseMapper::toDto)
                .orElseThrow(() -> new NotFoundException(
                        "User with username %s was not found".formatted(username)));
    }

    @Override
    public UserAccountResponseDto updateUserAccount(UpdateUserAccountRequestDto userAccountRequestDto) {
        String username = getUsernameFromUserDetails();
        UserAccount userAccount = userAccountRepository
                .findByUsername(username)
                .map(account -> {
                    UserAccount updatedAccount = updateUserAccountMapper
                            .updateUserAccount(userAccountRequestDto, account);
                    updatedAccount.setPassword(passwordEncoder.encode(userAccountRequestDto.password()));
                    return userAccountRepository.save(updatedAccount);
                })
                .orElseThrow(() -> new NotFoundException(
                        "User with %d id was not found".formatted(username)));
        return userAccountResponseMapper.toDto(userAccount);
    }

    private String getUsernameFromUserDetails() {
        return SecurityContextHolder.getContext().getAuthentication().getName();
    }
}
