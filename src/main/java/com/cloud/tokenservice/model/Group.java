package com.cloud.tokenservice.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
public class Group implements Serializable {
    private String token;
    private int    percentage;
}
