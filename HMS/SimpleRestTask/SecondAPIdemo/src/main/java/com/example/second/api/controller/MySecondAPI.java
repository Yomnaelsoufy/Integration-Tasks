package com.example.second.api.controller;

import java.util.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
@RequestMapping("/v1/tahaqoq/mobile-verification")
public class MySecondAPI {
    private static final Logger logger = LoggerFactory.getLogger(MySecondAPI.class);

    private static final ObjectMapper objectMapper = new ObjectMapper();

    @GetMapping
    public ResponseEntity getVerification(
            @RequestParam("IDType") String idType,
            @RequestParam("IDNumber") String idNumber,
            @RequestParam("mobileNumber") String mobileNumber) {
        Map<String, Object> body = new HashMap<>();
        Map<String, Object> header = new HashMap<>();
        header.put("requestId", "string");
        Map<String, Object> status = new HashMap<>();
        status.put("code", "I000000");
        status.put("details", "Success");
        body.put("isOwner", "true");
        header.put("status", status);
        Map<String, Object> response = new HashMap<>();
        response.put("body", body);
        response.put("header", header);
        logResponse(response);
        return ResponseEntity.ok(response);
    }

    private void logResponse(Map<String, Object> responseBody) {
        // Log the response body as JSON
        try {
            String responseBodyJson = objectMapper.writeValueAsString(responseBody);
            logger.info("StatusCode:200 ok, RequestID:4356765, HTTP Method: Get, Response Body: {}", responseBodyJson);
        } catch (JsonProcessingException e) {
            logger.error("Error serializing response body to JSON: {}", e.getMessage());
        }
    }

}
