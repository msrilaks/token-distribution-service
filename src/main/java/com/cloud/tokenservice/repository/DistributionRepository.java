package com.cloud.tokenservice.repository;

import com.cloud.tokenservice.error.ErrorCodes;
import com.cloud.tokenservice.exception.DistributionException;
import com.cloud.tokenservice.model.Distribution;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.core.SetOperations;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Set;
import java.util.UUID;

@Component
public class DistributionRepository {
    private static final Logger LOGGER = LoggerFactory.getLogger(
            DistributionRepository.class.getName());

    @Resource(name = "redisTemplate")
    private SetOperations<UUID, Distribution> setOps;


    public void createDistribution(Distribution distribution) {
        setOps.add(distribution.getId(), distribution);
    }

    public void deleteDistribution(UUID distributionId) {
        setOps.pop(distributionId);
    }

    public Distribution getDistribution(UUID distributionId) {
        try {
            Set<Distribution> values = setOps.members(distributionId);
            return values.iterator()
                         .next();
        } catch (Exception e) {
            throw new DistributionException(
                    ErrorCodes.DISTRIBUTION_NOT_FOUND.constructError(), e);
        }
    }
}
