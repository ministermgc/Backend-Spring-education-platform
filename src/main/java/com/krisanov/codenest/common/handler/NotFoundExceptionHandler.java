package com.krisanov.codenest.common.handler;

import com.krisanov.codenest.common.dto.ResponseErrorDto;
import com.krisanov.codenest.common.exception.NotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;

@RestControllerAdvice
@Slf4j
public class NotFoundExceptionHandler {

    @ExceptionHandler(NotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseErrorDto handleMethodArgumentNotValidException(
            NotFoundException ex) {
        log.error("[EXCEPTION] message: {}", ex.getMessage());

        String errorMessages = ex.getMessage();

        return ResponseErrorDto.builder()
                .status(HttpStatus.NOT_FOUND)
                .errorMessage(List.of(errorMessages))
                .build();
    }
}
