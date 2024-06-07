package com.krisanov.codenest.common.handler;

import com.krisanov.codenest.common.dto.ResponseErrorDto;
import com.krisanov.codenest.common.exception.NotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
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
            MethodArgumentNotValidException ex) {
        log.error("[EXCEPTION] message: {}", ex.getMessage());

        List<String> errorMessages = ex.getBindingResult().getFieldErrors()
                .stream()
                .map(fieldError -> String.format("Field '%s' %s", fieldError.getField(),
                        fieldError.getDefaultMessage()))
                .toList();

        return ResponseErrorDto.builder()
                .status(HttpStatus.NOT_FOUND)
                .errorMessage(errorMessages)
                .build();
    }
}
