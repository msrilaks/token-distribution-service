package com.cloud.tokenservice.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
public class TokenRingRepository {
    @Autowired
    private RedisTemplate<String, String> template;

    @Resource(name="redisTemplate")
    private ListOperations<String, String> listOps;

    public void addToken(String token) {
        listOps.leftPush("sri", token);

        String val = listOps.leftPop("sri");
        System.out.println("SRI: " + val);
    }
}
