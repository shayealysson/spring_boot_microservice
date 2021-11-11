package com.example.springbootconfig;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RefreshScope
public class GreetingController {

    @Value("${my.greeting1: Displaying defaul value, my.greeting1 does not exist in properties}")
    private String greetingMessage;

    @Value("${my.list.properties}")
    private List<String> myList;

    @Autowired
    public DbSettings dbSettings;

    @Autowired
    private Environment env;

    @GetMapping("/greeting")
    public String greeting() {
        return ""+dbSettings.getConnection()+" "+dbSettings.getHost()+" "+dbSettings.getPort();
    }

    @GetMapping("/env")
    public String envDetails() {
        return env.toString();
    }
    
}
