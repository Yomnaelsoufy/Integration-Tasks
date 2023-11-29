package com.example.first.api.controller;

import java.util.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
@RequestMapping("/v1/consumers/{consumerId}/id-verification")
public class MyFirstController {
    private static final Logger logger = LoggerFactory.getLogger(MyFirstController.class);

    private static final ObjectMapper objectMapper = new ObjectMapper();

    @PostMapping("/validate")
    public ResponseEntity validateIdVerification(@PathVariable String consumerId,
            @RequestBody Map<String, Object> requestBody) {

        logRequest(requestBody);
        Map<String, Object> header = new HashMap<>();
        header.put("requestId", UUID.randomUUID().toString());
        Map<String, Object> status = new HashMap<>();
        status.put("code", "I000000");
        status.put("description", "Success");
        header.put("status", status);
        Map<String, Object> data = new HashMap<>();
        Map<String, Object> poi = new HashMap<>();
        poi.put("poiNumber", "1079448994");
        poi.put("poiType", "IQA");
        data.put("poi", poi);
        data.put("isTahakookVerified", "false");
        data.put("mobileNumber", "00966568472298");
        Map<String, Object> response = new HashMap<>();
        response.put("header", header);
        response.put("data", data);
        // response.put("url",consumerId );

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