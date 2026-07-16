package com.example.accessmap.ramp;

import com.example.accessmap.common.geo.GeoPointFactory;
import com.example.accessmap.ramp.dto.CreateRampRequest;
import com.example.accessmap.ramp.dto.RampResponse;
import org.springframework.stereotype.Component;

@Component
public class RampMapper {

    private final GeoPointFactory geoPointFactory;

    public RampMapper(GeoPointFactory geoPointFactory) {
        this.geoPointFactory = geoPointFactory;
    }

    public RampEntity toEntity(CreateRampRequest request) {
        RampEntity entity = new RampEntity();
        entity.setLocation(geoPointFactory.create(request.latitude(), request.longitude()));
        entity.setCurbHeight(request.curbHeight());
        entity.setRoadAccess(request.roadAccess());
        entity.setWalkerBarrier(request.walkerBarrier());
        entity.setTactileTile(request.tactileTile());
        entity.setRampWidth(request.rampWidth());
        entity.setAngleSlant(request.angleSlant());
        return entity;
    }

    public RampResponse toResponse(RampEntity entity) {
        return new RampResponse(
                entity.getId(),
                geoPointFactory.latitude(entity.getLocation()),
                geoPointFactory.longitude(entity.getLocation()),
                entity.getCurbHeight(),
                entity.isRoadAccess(),
                entity.isWalkerBarrier(),
                entity.isTactileTile(),
                entity.getRampWidth(),
                entity.getAngleSlant(),
                entity.getCreatedAt(),
                entity.getCreatedBy()
        );
    }
}
