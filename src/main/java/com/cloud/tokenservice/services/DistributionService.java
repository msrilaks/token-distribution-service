package com.cloud.tokenservice.services;

import com.cloud.tokenservice.model.Distribution;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class DistributionService {
    public void createDistribution(Distribution distribution) {
        distribution.id = UUID.randomUUID();
    }

    public void updateDistribution(
            UUID distributionId,
            Distribution distribution) {
    }

    public void deleteDistribution(UUID distributionId) {
    }
}
