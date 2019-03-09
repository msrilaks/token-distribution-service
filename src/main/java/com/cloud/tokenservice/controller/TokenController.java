package com.cloud.tokenservice.controller;

import com.cloud.tokenservice.service.TokenService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@Api(value = "Token", description = "REST API for Token", tags = {"Token"})
public class TokenController {
    @Autowired
    TokenService tokenService;

    @GetMapping("/distributions/{distributionId}/token")
    @ApiOperation(value = "Get a token", tags = {"Token"})
    public String getToken(
            @PathVariable("distributionId")
                    UUID distributionId) {
        return tokenService.getToken(distributionId);
    }

}