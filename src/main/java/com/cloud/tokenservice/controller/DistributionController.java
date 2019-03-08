package com.cloud.tokenservice.controller;

import com.cloud.tokenservice.model.Distribution;
import com.cloud.tokenservice.service.DistributionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@Controller
public class DistributionController {
    @Autowired
    DistributionService distributionService;

    @PostMapping(path = "/distributions", consumes = "application/json",
                 produces = "application/json")
    public ResponseEntity<Distribution> createDistribution(
            @RequestBody Distribution distribution) {
        distributionService.createDistribution(distribution);
        return ResponseEntity.ok(distribution);
    }

    @PutMapping(path = "/distributions", consumes = "application/json",
                produces = "application/json")
    public ResponseEntity<Distribution> updateDistribution(
            @PathVariable("distributionId") UUID distributionId,
            @RequestBody Distribution distribution) {
        distributionService.updateDistribution(distributionId, distribution);
        return ResponseEntity.ok(distribution);
    }

    @DeleteMapping(path = "/distributions/{distributionId}", consumes =
            "application/json", produces = "application/json")
    public ResponseEntity deleteDistribution(
            @PathVariable("distributionId") UUID distributionId) {
        distributionService.deleteDistribution(distributionId);
        return ResponseEntity.ok().build();
    }
}
