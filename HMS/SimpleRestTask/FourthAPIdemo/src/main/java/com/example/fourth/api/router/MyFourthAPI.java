package com.example.fourth.api.router;

import java.util.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
@RequestMapping("/v1/nafath-app/id-verification")
public class MyFourthAPI {
    private static final Logger logger = LoggerFactory.getLogger(MyFourthAPI.class);

    private static final ObjectMapper objectMapper = new ObjectMapper();

    @PostMapping("/initiate")
    public ResponseEntity MyFourthAPI(@RequestBody Map<String, Object> requestBody) {

        logRequest(requestBody);
        Map<String, Object> header = new HashMap<>();
        header.put("requestId", UUID.randomUUID().toString());
        Map<String, Object> status = new HashMap<>();
        status.put("code", "I000000");
        status.put("description", "Success");
        header.put("status", status);
        Map<String, Object> body = new HashMap<>();
        body.put("transId", UUID.randomUUID().toString());
        body.put("random", new Random().nextInt(100));
        Map<String, Object> response = new HashMap<>();
        response.put("header", header);
        response.put("body", body);

        logResponse(response);

        return ResponseEntity.ok(response);
    }

    private void logRequest(Map<String, Object> requestBody) {
        // Log the request body as JSON
        try {
            String requestBodyJson = objectMapper.writeValueAsString(requestBody);
            logger.info("StatusCode:200 ok, RequestID:4356765, HTTP Method: Post, Request Body: {}", requestBodyJson);
        } catch (JsonProcessingException e) {
            logger.error("Error serializing request body to JSON: {}", e.getMessage());
        }
    }

    private void logResponse(Map<String, Object> responseBody) {
        // Log the response body as JSON
        try {
            String responseBodyJson = objectMapper.writeValueAsString(responseBody);
            logger.info("StatusCode:200 ok, RequestID:4356765, HTTP Method: Post, Response Body: {}", responseBodyJson);
        } catch (JsonProcessingException e) {
            logger.error("Error serializing response body to JSON: {}", e.getMessage());
        }
    }
}