package com.breaker.circuitbreaker.service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.net.URI;

@Service
@Slf4j
public class ServiceA {

    RestTemplate restTemplate;
    public ServiceA(RestTemplateBuilder restTemplateBuilder){
        restTemplate=restTemplateBuilder.build();
    }
    //Implementation using hystrix
    @HystrixCommand(fallbackMethod = "reliable" ,commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "5000"),
            @HystrixProperty(name = "execution.isolation.thread.interruptOnTimeout", value = "true"),
            @HystrixProperty(name = "execution.isolation.strategy", value = "SEMAPHORE")
    })
     public String getProductList(String category){
        URI uri=URI.create("http://localhost:8080/getproducts");
        return restTemplate.getForObject(uri,String.class);
    }

    public String reliable(String s){
        return "Oops! Shop service has some issue";
    }
}
