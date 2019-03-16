package com.cloud.tokenservice.repository;

import com.cloud.tokenservice.error.ErrorCodes;
import com.cloud.tokenservice.exception.DistributionException;
import com.cloud.tokenservice.model.Distribution;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.SetOperations;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Set;
import java.util.UUID;

@Component
public class DistributionRepository {

    @Autowired
    private RedisTemplate redisTemplate;

    @Resource(name = "redisTemplate")
    private SetOperations<UUID, Distribution> setOps;


    public void createDistribution(Distribution distribution) {
        setOps.add(distribution.id, distribution);
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
