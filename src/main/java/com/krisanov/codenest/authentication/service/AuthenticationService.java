package com.krisanov.codenest.authentication.service;

import com.krisanov.codenest.authentication.dto.AuthenticationRequestDto;
import com.krisanov.codenest.authentication.dto.AuthenticationResponseDto;
import com.krisanov.codenest.authentication.dto.RegistrationRequestDto;
import jakarta.servlet.http.HttpServletResponse;

public interface AuthenticationService {

    /**
     * Authenticates a user based on the provided authentication request.
     *
     * @param authenticationRequestDTO the request object containing authentication details
     * @param response the HTTP response object
     * @return the authentication response containing the authentication result and tokens
     */
    AuthenticationResponseDto authenticate(AuthenticationRequestDto authenticationRequestDTO,
                                           HttpServletResponse response);

    /**
     * Registers a new user based on the provided registration request.
     *
     * @param registrationRequestDTO the request object containing registration details
     * @return the authentication response containing the registration result and tokens
     */
    AuthenticationResponseDto register(RegistrationRequestDto registrationRequestDTO);

    /**
     * Refreshes the access token using the provided refresh token.
     *
     * @param refreshToken the refresh token used to obtain a new access token
     * @return the authentication response containing the new access token
     */
    AuthenticationResponseDto refreshAccessToken(String refreshToken);
}
