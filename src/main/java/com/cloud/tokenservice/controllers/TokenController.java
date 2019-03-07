package com.cloud.tokenservice.controllers;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
public class TokenController {

    @RequestMapping("/tokens")
    public String getTokens() {
        return "Greetings from Spring Boot!";
    }

}