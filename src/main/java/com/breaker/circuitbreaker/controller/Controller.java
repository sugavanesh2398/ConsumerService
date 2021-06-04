package com.breaker.circuitbreaker.controller;

import com.breaker.circuitbreaker.service.ServiceB;
import com.breaker.circuitbreaker.service.ServiceA;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class Controller {
    @Autowired
    ServiceA servieA;
    @Autowired
    ServiceB serviceB;

    @RequestMapping(method = RequestMethod.GET,path = "/listproducts")
    public String listProducts(@RequestParam(name = "category") String category) {
        return servieA.getProductList(category);
    }

    @RequestMapping(method = RequestMethod.GET,path = "/getProductDetails")
    public String getProductDetails(@RequestParam(name = "product") String product){
        return serviceB.getProductDetails(product);
    }
}
