package com.cloud.tokenservice.service;

import com.cloud.tokenservice.model.Distribution;
import com.cloud.tokenservice.repository.TokenRingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class DistributionService {
    @Autowired
    TokenRingRepository tokenRingRepository;

    public void createDistribution(Distribution distribution) {
        distribution.id = UUID.randomUUID();
        tokenRingRepository.createDistribution(distribution);
    }

    public void updateDistribution(
            UUID distributionId, Distribution distribution) {
        distribution.id = distributionId;
        tokenRingRepository.updateDistribution(distributionId, distribution);
    }

    public void deleteDistribution(UUID distributionId) {
    }

    public Distribution getDistribution(UUID distributionId) {
        return tokenRingRepository.getDistribution(distributionId);
    }
}