package com.daneshvar.logging;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.context.annotation.PropertySource;

@PropertySource("classpath:application.yml")
@ConfigurationPropertiesScan
@SpringBootApplication
public class LoggingCallmateApplication {

    public static void main(String[] args) {
        SpringApplication.run(LoggingCallmateApplication.class, args);
    }

}
