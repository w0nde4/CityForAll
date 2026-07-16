package com.example.accessmap.road.dto;

import java.time.Instant;

public record RoadResponse(
        Long id,
        double latitude,
        double longitude,
        Integer trafficLanes,
        Long surfaceQuality,
        boolean tramTracks,
        Double railHeight,
        boolean trafficLight,
        Boolean audibleTrafficLight,
        Boolean countdownTimer,
        Instant createdAt,
        String createdBy
) {
}
