package com.example.second.api.controller;

import java.util.*;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/tahaqoq/mobile-verification")
public class MySecondAPI {

	@GetMapping
	public ResponseEntity getVerification(
			@RequestParam("IDType") String idType,
		    @RequestParam("IDNumber") String idNumber,
		    @RequestParam("mobileNumber") String mobileNumber) {
//		System.out.println("IDType: " + idType);
//	    System.out.println("IDNumber: " + idNumber);
//	    System.out.println("MobileNumber: " + mobileNumber);
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
        return ResponseEntity.ok(response);
	}
	
}
