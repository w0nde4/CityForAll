package com.example.accessmap.road;

import com.example.accessmap.common.geo.GeoPointFactory;
import com.example.accessmap.road.dto.CreateRoadRequest;
import com.example.accessmap.road.dto.RoadResponse;
import org.springframework.stereotype.Component;

@Component
public class RoadMapper {

    private final GeoPointFactory geoPointFactory;

    public RoadMapper(GeoPointFactory geoPointFactory) {
        this.geoPointFactory = geoPointFactory;
    }

    public RoadEntity toEntity(CreateRoadRequest request) {
        RoadEntity entity = new RoadEntity();
        entity.setLocation(geoPointFactory.create(request.latitude(), request.longitude()));
        entity.setTrafficLanes(request.trafficLanes());
        entity.setSurfaceQuality(request.surfaceQuality());
        entity.setTramTracks(request.tramTracks());
        entity.setRailHeight(request.railHeight());
        entity.setTrafficLight(request.trafficLight());
        entity.setAudibleTrafficLight(request.audibleTrafficLight());
        entity.setCountdownTimer(request.countdownTimer());
        return entity;
    }

    public RoadResponse toResponse(RoadEntity entity) {
        return new RoadResponse(
                entity.getId(),
                geoPointFactory.latitude(entity.getLocation()),
                geoPointFactory.longitude(entity.getLocation()),
                entity.getTrafficLanes(),
                entity.getSurfaceQuality(),
                entity.isTramTracks(),
                entity.getRailHeight(),
                entity.isTrafficLight(),
                entity.getAudibleTrafficLight(),
                entity.getCountdownTimer(),
                entity.getCreatedAt(),
                entity.getCreatedBy()
        );
    }
}
