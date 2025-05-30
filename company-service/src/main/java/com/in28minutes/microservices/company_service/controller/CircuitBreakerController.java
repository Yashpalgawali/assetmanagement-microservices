package com.in28minutes.microservices.company_service.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.github.resilience4j.retry.annotation.Retry;

@RestController
public class CircuitBreakerController {

	private Logger logger = LoggerFactory.getLogger(CircuitBreakerController.class);
	
	@GetMapping("sample-api")
	//@Retry(name="sample-api",fallbackMethod = "hardCodedResponse")
	@CircuitBreaker(name="default",fallbackMethod = "hardCodedResponse")
	@RateLimiter(name="default")
	public String sampleApi() {
		logger.info("sampl api call received");
//		ResponseEntity<String> forEntity = new RestTemplate().getForEntity("http://localhost:7474/", String.class);
//		return forEntity.getBody();
		return "SAMPLE_API CALLED";
	}
	
	public String hardCodedResponse(Exception e) {
		return "Fall Back Response - Circuitbreaker used";
	}
}
