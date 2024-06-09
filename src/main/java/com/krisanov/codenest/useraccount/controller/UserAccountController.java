package com.krisanov.codenest.useraccount.controller;

import com.krisanov.codenest.common.dto.ResponseErrorDto;
import com.krisanov.codenest.useraccount.dto.UpdateUserAccountRequestDto;
import com.krisanov.codenest.useraccount.dto.UserAccountResponseDto;
import com.krisanov.codenest.useraccount.service.UserAccountService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "User account management")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/user")
public class UserAccountController {

    private final UserAccountService userAccountService;

    @Operation(
            description = "Getting a user account.",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            content = @Content(
                                    schema = @Schema(implementation = UserAccountResponseDto.class)
                            )
                    ),
                    @ApiResponse(
                            responseCode = "401",
                            description = "The user must be authenticated to perform this action.",
                            content = @Content(
                                    schema = @Schema(implementation = ResponseErrorDto.class)
                            )
                    )
            }
    )
    @GetMapping
    public UserAccountResponseDto getUserAccountById() {
        return userAccountService.findUserAccount();
    }

    @Operation(
            description = "Getting a user account.",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            content = @Content(
                                    schema = @Schema(implementation = UserAccountResponseDto.class)
                            )
                    ),
                    @ApiResponse(
                            responseCode = "400",
                            description = "In case the user data is not valid.",
                            content = @Content(
                                    schema = @Schema(implementation = ResponseErrorDto.class)
                            )
                    ),
                    @ApiResponse(
                            responseCode = "401",
                            description = "The user must be authenticated to perform this action.",
                            content = @Content(
                                    schema = @Schema(implementation = ResponseErrorDto.class)
                            )
                    )
            }
    )
    @PutMapping
    public UserAccountResponseDto updateUserAccount(
            @RequestBody @Valid UpdateUserAccountRequestDto updateUserAccountRequestDto
    ) {
        return userAccountService.updateUserAccount(updateUserAccountRequestDto);
    }
}