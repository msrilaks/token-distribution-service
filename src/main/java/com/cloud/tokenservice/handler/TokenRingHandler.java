package com.cloud.tokenservice.handler;

import com.cloud.tokenservice.model.Distribution;
import com.cloud.tokenservice.model.Group;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class TokenRingHandler {
    public List<String> createTokenRing(Distribution distribution) {
        List<String> tokenList = new ArrayList<>();
        for (Group group : distribution.getGroups()) {
            for (int i = 0; i < group.getPercentage(); i++) {
                tokenList.add(group.getToken());
            }
        }
        return tokenList;
    }
}
