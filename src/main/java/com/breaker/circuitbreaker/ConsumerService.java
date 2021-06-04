package com.breaker.circuitbreaker;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;

@EnableCircuitBreaker
@SpringBootApplication
@Slf4j
@EnableHystrixDashboard
public class ConsumerService {

	public static void main(String[] args) {
		SpringApplication.run(ConsumerService.class, args);
	}


}
