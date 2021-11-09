package com.example.springbootconfig;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GreetingController {

    @Value("${my.greeting1: Displaying defaul value, my.greeting1 does not exist in properties}")
    private String greetingMessage;

    @GetMapping("/greeting")
    public String greeting() {
        return greetingMessage;
    }
    
}
