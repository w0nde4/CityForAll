package com.example.accessmap.common.geo;

import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.GeometryFactory;
import org.locationtech.jts.geom.Point;
import org.locationtech.jts.geom.PrecisionModel;
import org.springframework.stereotype.Component;

/**
 * Общая точка входа для работы с геометрией точки (PostGIS/JTS).
 * Используется мапперами всех фич
 * ВАЖНО: в JTS Coordinate(x, y) = (долгота, широта). Это единственное
 * место в проекте, где эта путаница должна возникать — дальше везде
 * работаем через понятные latitude/longitude.
 */
@Component
public class GeoPointFactory {

    private static final int SRID_WGS84 = 4326;

    private final GeometryFactory geometryFactory =
            new GeometryFactory(new PrecisionModel(), SRID_WGS84);

    public Point create(double latitude, double longitude) {
        Point point = geometryFactory.createPoint(new Coordinate(longitude, latitude));
        point.setSRID(SRID_WGS84);
        return point;
    }

    public double latitude(Point point) {
        return point.getY();
    }

    public double longitude(Point point) {
        return point.getX();
    }
}
