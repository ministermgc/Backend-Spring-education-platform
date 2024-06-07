package com.krisanov.codenest.authentication.handler;

import com.krisanov.codenest.common.dto.ResponseErrorDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;

@RestControllerAdvice
@Slf4j
public class BadCredentialsExceptionHandler {

    @ExceptionHandler(BadCredentialsException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseErrorDto handleMethodArgumentNotValidException(
            BadCredentialsException ex) {
        log.error("[EXCEPTION] message: {}", ex.getMessage());

        String errorMessages = ex.getMessage();

        return ResponseErrorDto.builder()
                .status(HttpStatus.BAD_REQUEST)
                .errorMessage(List.of(errorMessages))
                .build();
    }
}
