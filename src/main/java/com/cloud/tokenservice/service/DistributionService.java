package com.cloud.tokenservice.service;

import com.cloud.tokenservice.model.Distribution;
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

    public void createDistribution(Distribution distribution) {
        distribution.setId(UUID.randomUUID());
        distributionRepository.createDistribution(distribution);
        tokenRingRepository.createTokenRing(distribution);
    }

    public void updateDistribution(
            UUID distributionId, Distribution distribution) {
        distribution.setId(distributionId);
        tokenRingRepository.updateTokenRing(distributionId, distribution);
    }

    public void deleteDistribution(UUID distributionId) {
        tokenRingRepository.deleteTokenRing(distributionId);
        distributionRepository.deleteDistribution(distributionId);
    }

    public Distribution getDistribution(UUID distributionId) {
        return distributionRepository.getDistribution(distributionId);
    }
}
