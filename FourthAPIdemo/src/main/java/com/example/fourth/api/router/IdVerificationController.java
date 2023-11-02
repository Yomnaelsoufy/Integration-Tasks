package com.example.fourth.api.router;

import java.util.*;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/nafath-app/id-verification")
public class IdVerificationController {

    @PostMapping("/initiate")
    public ResponseEntity initiateVerification() {
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
        return ResponseEntity.ok(response);
    }
}