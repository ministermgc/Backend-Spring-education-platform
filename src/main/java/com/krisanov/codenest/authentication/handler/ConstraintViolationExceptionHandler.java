package com.krisanov.codenest.authentication.handler;

import com.krisanov.codenest.common.dto.ResponseErrorDto;
import jakarta.validation.ConstraintViolationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;

@RestControllerAdvice
@Slf4j
public class ConstraintViolationExceptionHandler {

    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public ResponseErrorDto handleMethodArgumentNotValidException(
            ConstraintViolationException ex) {
        log.error("[EXCEPTION] message: {}", ex.getMessage());

        String errorMessages = ex.getMessage();

        return ResponseErrorDto.builder()
                .status(HttpStatus.CONFLICT)
                .errorMessage(List.of(errorMessages))
                .build();
    }
}
