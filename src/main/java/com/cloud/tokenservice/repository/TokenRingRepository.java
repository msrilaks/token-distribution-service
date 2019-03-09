package com.cloud.tokenservice.repository;

import com.cloud.tokenservice.model.Distribution;
import com.cloud.tokenservice.model.Group;
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

    public String getToken(String distributionId) {
        return listOps.rightPopAndLeftPush(distributionId, distributionId);
    }

    public void create(Distribution distribution) {
        for(Group group: distribution.groups) {
            for(int i=0;i<group.percentage;i++){
                System.out.println("SRI: " + group.token.toString());
                listOps.leftPush(distribution.id.toString(),
                                 group.token.token.toString());
            }
        }
    }
}
