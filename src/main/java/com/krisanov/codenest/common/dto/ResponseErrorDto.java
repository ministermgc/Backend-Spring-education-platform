package com.krisanov.codenest.common.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class ResponseErrorDto {

    private HttpStatus status;

    @JsonProperty("error_message")
    private List<String> errorMessage;
}
