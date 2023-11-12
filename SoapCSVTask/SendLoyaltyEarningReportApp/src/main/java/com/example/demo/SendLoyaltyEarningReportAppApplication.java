package com.example.demo;

import org.apache.camel.spring.boot.vault.HashicorpVaultAutoConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(exclude = { HashicorpVaultAutoConfiguration.class })
public class SendLoyaltyEarningReportAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(SendLoyaltyEarningReportAppApplication.class, args);
	}

}
