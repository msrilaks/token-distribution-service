package com.cloud.tokenservice.repository;

import com.cloud.tokenservice.model.Distribution;
import com.cloud.tokenservice.model.Group;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.UUID;

@Component
public class TokenRingRepository {
    @Autowired
    private RedisTemplate<String, String> template;

    @Resource(name = "redisTemplate")
    private ListOperations<String, String> listOps;

    public String getToken(String distributionId) {
        return listOps.rightPopAndLeftPush(distributionId, distributionId);
    }

    public void createDistribution(Distribution distribution) {
        populateDistributionList(distribution, distribution.id);
    }

    public void updateDistribution(
            UUID distributionId, Distribution distribution) {
        listOps.trim(distributionId.toString(), -1, 0);
        populateDistributionList(distribution, distributionId);
    }

    private void populateDistributionList(Distribution distribution, UUID id) {
        for (Group group : distribution.groups) {
            for (int i = 0; i < group.percentage; i++) {
                listOps.leftPush(id.toString(), group.token);
            }
        }
    }

    public Distribution getDistribution(UUID distributionId) {
        return null;
    }
}
