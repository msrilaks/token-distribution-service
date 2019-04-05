package com.cloud.tokenservice.model;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class TokenRing {
    private List<String> tokenList = new ArrayList<>();

    public void add(String token, int count) {
        for (int i = 0; i < count; i++) {
            tokenList.add(token);
        }
    }

    @Override
    public String toString() {
        return tokenList.stream()
                        .map(a -> a + " ")
                        .collect(Collectors.joining(""));
    }

    public List<String> getAsList() {
        return tokenList;
    }
}
