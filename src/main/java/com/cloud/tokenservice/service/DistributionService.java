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
        tokenRingRepository.create(distribution);
    }

    public void updateDistribution(
            UUID distributionId,
            Distribution distribution) {
    }

    public void deleteDistribution(UUID distributionId) {
    }
}
