package com.cloud.tokenservice.repository;

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
        Set<Distribution> values = setOps.members(distributionId);
        return (values == null) ? null : values.iterator()
                                               .next();
    }
}
