package com.cloud.tokenservice.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
public class Distribution implements Serializable {
    private UUID        id;
    private String      name;
    private List<Group> groups;

    public Map<String, Integer> getAsMap() {
        Map<String, Integer> distMap = new HashMap<>();
        groups.forEach(group -> {
            distMap.put(group.getToken(), group.getPercentage());
        });
        return distMap;
    }
}
