package com.cloud.tokenservice.handler;

import com.cloud.tokenservice.model.Distribution;
import com.cloud.tokenservice.model.TokenRing;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class TokenRingHandler {
    private static final Logger LOGGER = LoggerFactory.getLogger(
            TokenRingHandler.class.getName());

    public TokenRing createTokenRing(Distribution distribution) {
        TokenRing tokenRing = new TokenRing();
        Map<String, Integer> distMap = getDistributionAsMap(distribution);
        Map<String, Integer> distributionWeightMap = getDistributionWeightMap(
                distribution);
        for (int i = 0; i < 10; i++) {
            addTokens(distribution, tokenRing, distMap, distributionWeightMap);
        }
        LOGGER.info(
                "Token Ring constructed for " + distribution.getId() + " : " +
                tokenRing);
        return tokenRing;
    }

    private void addTokens(
            Distribution distribution, TokenRing tokenRing,
            Map<String, Integer> distMap,
            Map<String, Integer> distributionWeightMap) {

        distMap.keySet()
               .iterator()
               .forEachRemaining(token -> {
                   int tokenCount = distMap.get(token) / 10;
                   int weight = distMap.get(token) % 10;
                   int oldWeight = distributionWeightMap.get(token);
                   if (weight + oldWeight >= 10) {
                       LOGGER.info("Token Ring Distribution: " +
                                   distribution.getId() + " token: " + token +
                                   " weight: " + weight + " oldWeight:" +
                                   oldWeight);
                       distributionWeightMap.put(token,
                                                 (weight + oldWeight) - 10);
                       tokenCount++;
                   } else {
                       LOGGER.info("Token Ring Distribution: " +
                                   distribution.getId() + " token: " + token +
                                   " weight: " + weight + " oldWeight:" +
                                   oldWeight);
                       distributionWeightMap.put(token, (weight + oldWeight));
                   }
                   tokenRing.add(token, tokenCount);

                   LOGGER.info(
                           "Token Ring Distribution: " + distribution.getId() +
                           " token: " + token + "tokenCount: " + tokenCount);
               });
    }

    private Map<String, Integer> getDistributionAsMap(
            Distribution distribution) {
        Map<String, Integer> distMap = new HashMap<>();
        distribution.getGroups()
                    .forEach(group -> {
                        distMap.put(group.getToken(), group.getPercentage());
                    });
        return distMap;
    }

    private Map<String, Integer> getDistributionWeightMap(
            Distribution distribution) {
        Map<String, Integer> distWeightMap = new HashMap<>();
        distribution.getGroups()
                    .forEach(group -> {
                        distWeightMap.put(group.getToken(), 0);
                    });
        return distWeightMap;
    }
}
