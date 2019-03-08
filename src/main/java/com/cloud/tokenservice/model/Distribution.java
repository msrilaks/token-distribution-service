package com.cloud.tokenservice.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
public class Distribution {
    public UUID        id;
    public String      name;
    public List<Group> groups;
}
