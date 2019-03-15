package com.cloud.tokenservice.repository;

import com.cloud.tokenservice.model.Distribution;
import com.cloud.tokenservice.model.Group;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class TokenRingRepository {

    @Autowired
    private StringRedisTemplate redisTemplate;

    public String getToken(String distributionId) {
        return redisTemplate.opsForList()
                            .rightPopAndLeftPush(distributionId,
                                                 distributionId);
    }

    public void createTokenRing(Distribution distribution) {
        populateTokenRing(distribution);
    }

    public void updateTokenRing(
            UUID distributionId, Distribution distribution) {
        redisTemplate.opsForList()
                     .trim(distributionId.toString(), -1, 0);
        populateTokenRing(distribution);
    }

    private void populateTokenRing(Distribution distribution) {
        for (Group group : distribution.groups) {
            for (int i = 0; i < group.percentage; i++) {
                String token = group.token;
                redisTemplate.opsForList()
                             .leftPush(distribution.id.toString(), token);
            }
        }
    }

    public void deleteTokenRing(UUID distributionId) {
        redisTemplate.opsForList()
                     .trim(distributionId.toString(), -1, 0);
    }
}
