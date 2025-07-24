package com.daneshvar.logging.model.controller;

import com.daneshvar.logging.model.dto.LogMessage;
import jakarta.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("log")
public class LoggingController {

    private static final Logger logger = LoggerFactory.getLogger(LoggingController.class);

    @Value("${logging.logstash.url}")
    private String logstashUrl;


    @PostConstruct
    public void init() {
        System.out.println("Logstash URL: " + logstashUrl);
    }
    @PostMapping
    public ResponseEntity<String> receiveLog(@RequestBody LogMessage logMessage){
        try {
            switch (logMessage.getErrorLevel()){
                case ERROR -> logger.error("Error received from {}: {}",logMessage.getServiceName(),logMessage.getMessage());
                case INFO ->  logger.info("Info received from {}: {}",logMessage.getServiceName(),logMessage.getMessage());
                case WARN -> logger.warn("Warning received from {}: {}",logMessage.getServiceName(),logMessage.getMessage());
            }
            init();
            return ResponseEntity.ok("Log received");

        }catch (Exception e){
            logger.error("Exception while processing log: ",e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("error processing log");
        }


    }


}   
