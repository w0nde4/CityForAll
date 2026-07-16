package com.example.accessmap.road.dto;

import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

/**
 * Тело POST /api/roads. rail_height / audible_traffic_light / countdown_timer
 * не помечены @NotNull — они условные (null, если tram_tracks / traffic_light
 * соответственно == false), аналогично ramp_width/angle_slant у ramp.
 */
public record CreateRoadRequest(

        @NotNull @DecimalMin("-90.0") @DecimalMax("90.0")
        Double latitude,

        @NotNull @DecimalMin("-180.0") @DecimalMax("180.0")
        Double longitude,

        @NotNull @Min(0)
        Integer trafficLanes,

        @NotNull
        Long surfaceQuality,

        boolean tramTracks,
        Double railHeight,

        boolean trafficLight,
        Boolean audibleTrafficLight,
        Boolean countdownTimer
) {
}
