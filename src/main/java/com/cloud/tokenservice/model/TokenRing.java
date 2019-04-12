package com.cloud.tokenservice.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class TokenRing {
    private List<String>         tokenList      = new ArrayList<>();
    private Map<String, Integer> tokenCountsMap = new HashMap<>();

    public void add(String token, int count) {
        for (int i = 0; i < count; i++) {
            tokenList.add(token);
        }
        tokenCountsMap.put(token,
                           tokenCountsMap.getOrDefault(token, 0) + count);
    }

    @Override
    public String toString() {
        StringBuffer tokenCounts = new StringBuffer();
        tokenCountsMap.forEach((k, v) -> {
            tokenCounts.append(k)
                       .append("=")
                       .append(v)
                       .append(" ");
        });
        return "Token Counts :" + tokenCounts + ", Tokens: " +
               tokenList.stream()
                        .map(a -> a + " ")
                        .collect(Collectors.joining(""));
    }

    public List<String> getAsList() {
        return tokenList;
    }

}
