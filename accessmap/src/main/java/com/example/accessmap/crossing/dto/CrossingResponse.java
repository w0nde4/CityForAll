package com.example.accessmap.crossing.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.Instant;

public record CrossingResponse(
        Long id,
        double latitude,
        double longitude,
        Long entranceType,
        Long crossingType,
        boolean ramp,
        boolean liftingPlatform,
        Boolean platformWorking,

        @JsonProperty("handrails_below_70cm")
        boolean handrailsBelow70cm,

        boolean stairContrastMarking,
        boolean tactileStrip,
        boolean lighting,
        Long tactileTileCondition,
        Instant createdAt,
        String createdBy
) {
}
