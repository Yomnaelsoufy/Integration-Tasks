package com.example.third.api.controller;

import java.util.*;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/consumers/mobile")
public class MyThirdAPI {

    @PutMapping
    public ResponseEntity putMobileConsumer() {
        Map<String, Object> data = new HashMap<>();
        Map<String, Object> header = new HashMap<>();
        header.put("requestId", "3940396424452");
        Map<String, Object> status = new HashMap<>();
        status.put("code", "I000000");
        status.put("details", "Success");
        List<Map<String, Object>> subErrors = new ArrayList<>();
        // Map<String, Object> subError = new HashMap<>();
        // subError.put("code", "string");
        // subError.put("details", "string");
        // subErrors.add(subError);
        // status.put("subErrors", subErrors);
        header.put("status", status);
        Map<String, Object> response = new HashMap<>();
        response.put("data", "data");
        response.put("header", header);
        return ResponseEntity.ok(response);
    }
}