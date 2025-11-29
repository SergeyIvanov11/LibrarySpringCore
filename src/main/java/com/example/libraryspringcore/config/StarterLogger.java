package com.example.libraryspringcore.config;

import jakarta.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
public class StarterLogger {
    private static final Logger log = LoggerFactory.getLogger(StarterLogger.class);
    private final Environment env;

    public StarterLogger(Environment env) {
        this.env = env;
    }

    @PostConstruct
    public void init() {
        String[] profiles = env.getActiveProfiles();
        if (profiles.length == 0) {
            log.info("Приложение запущено без профиля");
        } else {
            log.info("Приложение запущено с профилем: " + String.join(", ", profiles));
        }
    }
}
