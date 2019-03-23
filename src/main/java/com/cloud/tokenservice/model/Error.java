package com.cloud.tokenservice.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class Error {
    private int    code;
    private String message;
    private String description;
}
