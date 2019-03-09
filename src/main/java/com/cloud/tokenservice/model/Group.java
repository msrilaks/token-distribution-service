package com.cloud.tokenservice.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class Group {
    public String token;
    public int    percentage;
}
