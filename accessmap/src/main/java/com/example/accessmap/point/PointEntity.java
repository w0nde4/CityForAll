package com.example.accessmap.point;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;
import org.locationtech.jts.geom.Point;

import java.time.Instant;
import java.util.HashMap;
import java.util.Map;

/**
 * Точка на карте, поставленная волонтёром.
 *
 * Хранение атрибутов сделано гибридным:
 *  - {@code location} — PostGIS geometry(Point, 4326). На неё потом лягут
 *    пространственные запросы и интеграция с GraphHopper.
 *  - {@code attributes} — jsonb со свободным набором дорожных атрибутов.
 *    Это даёт возможность добавлять новые атрибуты без миграций схемы.
 *    Когда какой-то атрибут "устаканится" и по нему понадобятся фильтры/индексы —
 *    выноси его в отдельную типизированную колонку.
 */
@Entity
@Table(name = "points")
@Getter
@Setter
public class PointEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Геометрия точки. Внимание: в JTS координата — это (x = longitude, y = latitude).
     * SRID 4326 = WGS84 (привычные градусы широты/долготы).
     */
    @Column(columnDefinition = "geometry(Point,4326)", nullable = false)
    private Point location;

    @JdbcTypeCode(SqlTypes.JSON)
    @Column(columnDefinition = "jsonb", nullable = false)
    private Map<String, Object> attributes = new HashMap<>();

    @Column(name = "created_at", nullable = false, updatable = false)
    private Instant createdAt;

    /** Кто поставил точку. Позже сюда подставится пользователь из контекста безопасности. */
    @Column(name = "created_by")
    private String createdBy;

    @PrePersist
    void onCreate() {
        if (createdAt == null) {
            createdAt = Instant.now();
        }
        if (attributes == null) {
            attributes = new HashMap<>();
        }
    }
}
