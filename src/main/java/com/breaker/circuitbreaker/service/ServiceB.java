package com.breaker.circuitbreaker.service;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.net.URI;

@Service
@Slf4j
public class ServiceB {
    RestTemplate restTemplate;

    public ServiceB(RestTemplateBuilder restTemplateBuilder) {
        restTemplate = restTemplateBuilder.build();
    }

    //Implementing using resilience4j
    @CircuitBreaker(name = "service", fallbackMethod = "reliable")
    public String getProductDetails(String category) {
        URI uri = URI.create("http://localhost:8080/getproducts");
        return restTemplate.getForObject(uri, String.class);
    }

    public String reliable(String s, Exception e) {
        return "Oops! Shop service has some issue";
    }

}
