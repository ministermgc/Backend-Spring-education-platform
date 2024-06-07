package com.krisanov.codenest.useraccount.service;

import com.krisanov.codenest.useraccount.dto.UpdateUserAccountRequestDto;
import com.krisanov.codenest.useraccount.dto.UserAccountResponseDto;

public interface UserAccountService {

    UserAccountResponseDto findUserAccount();

    UserAccountResponseDto updateUserAccount(UpdateUserAccountRequestDto userAccountRequestDto);
}
