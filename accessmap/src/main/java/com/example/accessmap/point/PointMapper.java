package com.example.accessmap.point;

import com.example.accessmap.point.dto.CreatePointRequest;
import com.example.accessmap.point.dto.PointResponse;
import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.GeometryFactory;
import org.locationtech.jts.geom.Point;
import org.locationtech.jts.geom.PrecisionModel;
import org.springframework.stereotype.Component;

import java.util.HashMap;

/**
 * Преобразование DTO <-> Entity. Держим в отдельном классе, чтобы сервис
 * не знал деталей геометрии, а контроллер — деталей хранения.
 */
@Component
public class PointMapper {

    private static final int SRID_WGS84 = 4326;

    /** Потокобезопасен, можно держать как один экземпляр. */
    private final GeometryFactory geometryFactory =
            new GeometryFactory(new PrecisionModel(), SRID_WGS84);

    public PointEntity toEntity(CreatePointRequest request) {
        PointEntity entity = new PointEntity();
        entity.setLocation(toPoint(request.latitude(), request.longitude()));
        entity.setAttributes(request.attributes() != null
                ? new HashMap<>(request.attributes())
                : new HashMap<>());
        return entity;
    }

    public PointResponse toResponse(PointEntity entity) {
        Point location = entity.getLocation();
        return new PointResponse(
                entity.getId(),
                location.getY(), // y = latitude
                location.getX(), // x = longitude
                entity.getAttributes(),
                entity.getCreatedAt(),
                entity.getCreatedBy()
        );
    }

    private Point toPoint(double latitude, double longitude) {
        // JTS: Coordinate(x, y) => (longitude, latitude). Лёгкий способ перепутать.
        Point point = geometryFactory.createPoint(new Coordinate(longitude, latitude));
        point.setSRID(SRID_WGS84);
        return point;
    }
}
