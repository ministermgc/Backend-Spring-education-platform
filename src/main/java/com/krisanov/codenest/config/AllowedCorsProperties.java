package com.krisanov.codenest.config;

import jakarta.annotation.PostConstruct;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.Arrays;
import java.util.List;

@ConfigurationProperties(prefix = "allowed")
@Getter
@Setter
public class AllowedCorsProperties {

    private List<String> cors;

    @PostConstruct
    private void splitString() {
        cors.forEach(s -> {
            String[] splitString = s.split(",");
            cors = Arrays.asList(splitString);
        });
    }
}
