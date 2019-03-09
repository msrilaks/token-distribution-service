package com.cloud.tokenservice.service;

import com.cloud.tokenservice.model.Distribution;
import com.cloud.tokenservice.repository.TokenRingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class TokenService {
    @Autowired
    TokenRingRepository tokenRingRepository;

    public String getToken(UUID distributionId) {
        return tokenRingRepository.getToken(distributionId.toString());
    }
}
