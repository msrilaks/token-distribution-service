package com.cloud.tokenservice.service;

import com.cloud.tokenservice.handler.TokenRingHandler;
import com.cloud.tokenservice.model.Distribution;
import com.cloud.tokenservice.model.TokenRing;
import com.cloud.tokenservice.repository.DistributionRepository;
import com.cloud.tokenservice.repository.TokenRingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class DistributionService {
    @Autowired
    TokenRingRepository tokenRingRepository;

    @Autowired
    DistributionRepository distributionRepository;

    @Autowired
    TokenRingHandler tokenRingHandler;

    public void createDistribution(Distribution distribution) {
        UUID distributionId = UUID.randomUUID();
        distribution.setId(distributionId);
        distributionRepository.createDistribution(distribution);
        TokenRing tokenRing = tokenRingHandler.createTokenRing(distribution);
        tokenRingRepository.createTokenRing(distributionId, tokenRing);
    }

    public void updateDistribution(
            UUID distributionId, Distribution distribution) {
        distribution.setId(distributionId);
        TokenRing tokenRing = tokenRingHandler.createTokenRing(distribution);
        tokenRingRepository.updateTokenRing(distributionId, tokenRing);
    }

    public void deleteDistribution(UUID distributionId) {
        tokenRingRepository.deleteTokenRing(distributionId);
        distributionRepository.deleteDistribution(distributionId);
    }

    public Distribution getDistribution(UUID distributionId) {
        return distributionRepository.getDistribution(distributionId);
    }
}
