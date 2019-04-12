package com.cloud.tokenservice.model;

import com.cloud.tokenservice.validator.DistributionConstraint;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@DistributionConstraint
public class Distribution implements Serializable {
    private UUID        id;
    private String      name;
    private List<Group> groups;

}
