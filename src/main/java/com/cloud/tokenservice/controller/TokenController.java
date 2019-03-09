package com.cloud.tokenservice.controller;

import com.cloud.tokenservice.service.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
public class TokenController {
    @Autowired
    TokenService tokenService;

    @GetMapping("/distributions/{distributionId}/token")
    public String getToken(
            @PathVariable("distributionId") UUID distributionId) {
        return tokenService.getToken(distributionId);
    }

}