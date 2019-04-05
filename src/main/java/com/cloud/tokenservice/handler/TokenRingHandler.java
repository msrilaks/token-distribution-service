package com.cloud.tokenservice.handler;

import com.cloud.tokenservice.model.Distribution;
import com.cloud.tokenservice.model.TokenRing;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class TokenRingHandler {
    private static final Logger LOGGER = LoggerFactory.getLogger(
            TokenRingHandler.class.getName());

    public TokenRing createTokenRing(Distribution distribution) {
        TokenRing tokenRing = new TokenRing();
        Map<String, Integer> distMap = distribution.getAsMap();

        for (int i = 0; i < 10; i++) {
            distMap.keySet()
                   .iterator()
                   .forEachRemaining(token -> {
                       int tokenCount = distMap.get(token) % 10;
                       tokenRing.add(token, tokenCount);
                   });

        }
        LOGGER.info(
                "Token Ring constructed for " + distribution.getId() + " : " +
                tokenRing);

        return tokenRing;
    }


}
