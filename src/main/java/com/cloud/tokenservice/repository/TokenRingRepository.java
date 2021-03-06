package com.cloud.tokenservice.repository;

import com.cloud.tokenservice.model.Token;
import com.cloud.tokenservice.model.TokenRing;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class TokenRingRepository {

    @Autowired
    private StringRedisTemplate redisTemplate;

    public Token getToken(String distributionId) {
        return new Token(redisTemplate.opsForList()
                                      .rightPopAndLeftPush(distributionId,
                                                           distributionId));
    }

    public void createTokenRing(UUID distributionId, TokenRing tokenRing) {
        for (String token : tokenRing.getAsList()) {
            redisTemplate.opsForList()
                         .leftPush(distributionId.toString(), token);
        }
    }

    public void updateTokenRing(
            UUID distributionId, TokenRing tokenRing) {
        deleteTokenRing(distributionId);
        createTokenRing(distributionId, tokenRing);
    }


    public void deleteTokenRing(UUID distributionId) {
        redisTemplate.opsForList()
                     .trim(distributionId.toString(), -1, 0);
    }

}
