package com.cloud.tokenservice.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
public class TokenController {

    @GetMapping("/distributions/{distributionId}/token")
    public String getToken(
            @PathVariable("distributionId") UUID distributionId) {
        return "Greetings from Spring Boot!";
    }

}