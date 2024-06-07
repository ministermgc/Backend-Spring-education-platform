package com.krisanov.codenest.authentication.controller;

import com.krisanov.codenest.authentication.dto.AuthenticationRequestDto;
import com.krisanov.codenest.authentication.dto.AuthenticationResponseDto;
import com.krisanov.codenest.authentication.dto.RefreshTokenDto;
import com.krisanov.codenest.authentication.dto.RegistrationRequestDto;
import com.krisanov.codenest.authentication.service.AuthenticationService;
import com.krisanov.codenest.common.dto.ResponseErrorDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Authentication")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/authentication")
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    @Operation(
            description = "Login to account.",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            content = @Content(
                                    schema = @Schema(implementation = AuthenticationResponseDto.class)
                            )
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "In case when user account is not found.",
                            content = @Content(
                                    schema = @Schema(implementation = ResponseErrorDto.class)
                            )
                    )
            }
    )
    @PostMapping("/login")
    public AuthenticationResponseDto login(
            @RequestBody @Valid AuthenticationRequestDto authenticationRequestDTO,
            HttpServletResponse response) {
        return authenticationService.authenticate(authenticationRequestDTO, response);
    }

    @Operation(
            description = "Register an account.",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            content = @Content(
                                    schema = @Schema(implementation = AuthenticationResponseDto.class)
                            )
                    ),
                    @ApiResponse(
                            responseCode = "400",
                            description = "In case an account with the same user name already exists.",
                            content = @Content(
                                    schema = @Schema(implementation = ResponseErrorDto.class)
                            )
                    )
            }
    )
    @PostMapping("/register")
    public AuthenticationResponseDto register(
            @RequestBody @Valid RegistrationRequestDto registrationRequestDTO) {
        return authenticationService.register(registrationRequestDTO);
    }

    @Operation(
            description = "Register an account.",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            content = @Content(
                                    schema = @Schema(implementation = AuthenticationResponseDto.class)
                            )
                    ),
                    @ApiResponse(
                            responseCode = "400",
                            description = "In case when refresh token is not valid.",
                            content = @Content(
                                    schema = @Schema(implementation = ResponseErrorDto.class)
                            )
                    )
            }
    )
    @PostMapping("/refresh")
    public AuthenticationResponseDto refreshAccessToken(@RequestBody RefreshTokenDto refreshTokenDto) {
        return authenticationService.refreshAccessToken(refreshTokenDto.refreshToken());
    }
}
