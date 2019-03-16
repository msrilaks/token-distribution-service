package com.cloud.tokenservice.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class Token {
    public String token;

    public Token(String token) {
        this.token = token;
    }
}
