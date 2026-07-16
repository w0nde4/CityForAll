package com.example.accessmap.crossing;

import com.example.accessmap.common.geo.GeoPointFactory;
import com.example.accessmap.crossing.dto.CreateCrossingRequest;
import com.example.accessmap.crossing.dto.CrossingResponse;
import org.springframework.stereotype.Component;

@Component
public class CrossingMapper {

    private final GeoPointFactory geoPointFactory;

    public CrossingMapper(GeoPointFactory geoPointFactory) {
        this.geoPointFactory = geoPointFactory;
    }

    public CrossingEntity toEntity(CreateCrossingRequest request) {
        CrossingEntity entity = new CrossingEntity();
        entity.setLocation(geoPointFactory.create(request.latitude(), request.longitude()));
        entity.setEntranceType(request.entranceType());
        entity.setCrossingType(request.crossingType());
        entity.setRamp(request.ramp());
        entity.setLiftingPlatform(request.liftingPlatform());
        entity.setPlatformWorking(request.platformWorking());
        entity.setHandrailsBelow70cm(request.handrailsBelow70cm());
        entity.setStairContrastMarking(request.stairContrastMarking());
        entity.setTactileStrip(request.tactileStrip());
        entity.setLighting(request.lighting());
        entity.setTactileTileCondition(request.tactileTileCondition());
        return entity;
    }

    public CrossingResponse toResponse(CrossingEntity entity) {
        return new CrossingResponse(
                entity.getId(),
                geoPointFactory.latitude(entity.getLocation()),
                geoPointFactory.longitude(entity.getLocation()),
                entity.getEntranceType(),
                entity.getCrossingType(),
                entity.isRamp(),
                entity.isLiftingPlatform(),
                entity.getPlatformWorking(),
                entity.isHandrailsBelow70cm(),
                entity.isStairContrastMarking(),
                entity.isTactileStrip(),
                entity.isLighting(),
                entity.getTactileTileCondition(),
                entity.getCreatedAt(),
                entity.getCreatedBy()
        );
    }
}
