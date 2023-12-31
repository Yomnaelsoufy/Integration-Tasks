package com.example.first.api.controller;

import java.util.*;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/consumers/{consumerId}/id-verification")
public class MyFirstController {

    @PostMapping("/validate")
    public ResponseEntity validateIdVerification(@PathVariable String consumerId) {
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
//        response.put("url",consumerId );
        return (ResponseEntity) ResponseEntity.ok(response);
    }
}