package com.example.third.api.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ArrayList;

@RestController
@RequestMapping("/v1/consumers/mobile")
public class MyThirdAPI {

    private static final Logger logger = LoggerFactory.getLogger(MyThirdAPI.class);
    private static final ObjectMapper objectMapper = new ObjectMapper();

    @PutMapping
    public ResponseEntity putMobileConsumer(@RequestBody Map<String, Object> requestBody) {
        // Log request information
        logRequest(requestBody);

        // Your existing logic here...

        Map<String, Object> data = new HashMap<>();
        Map<String, Object> header = new HashMap<>();
        header.put("requestId", "3940396424452");
        Map<String, Object> status = new HashMap<>();
        status.put("code", "I000000");
        status.put("details", "Success");
        List<Map<String, Object>> subErrors = new ArrayList<>();
        header.put("status", status);
        Map<String, Object> responseBody = new HashMap<>();
        responseBody.put("data", "data");
        responseBody.put("header", header);

        // Log response information
        logResponse(responseBody);

        return ResponseEntity.ok(responseBody);
    }

    private void logRequest(Map<String, Object> requestBody) {
        // Log the request body as JSON
        try {
            String requestBodyJson = objectMapper.writeValueAsString(requestBody);
            logger.info("Request Body: {}", requestBodyJson);
        } catch (JsonProcessingException e) {
            logger.error("Error serializing request body to JSON: {}", e.getMessage());
        }
    }

    private void logResponse(Map<String, Object> responseBody) {
        // Log the response body as JSON
        try {
            String responseBodyJson = objectMapper.writeValueAsString(responseBody);
            logger.info("Response Body: {}", responseBodyJson);
        } catch (JsonProcessingException e) {
            logger.error("Error serializing response body to JSON: {}", e.getMessage());
        }
    }
}
