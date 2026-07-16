package com.example.accessmap.common.entity;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.PrePersist;
import lombok.Getter;
import lombok.Setter;
import org.locationtech.jts.geom.Point;

import java.time.Instant;

/**
 * Общий предок для всех объектов, которые волонтёр ставит на карту
 * Каждый такой объект — это точка с координатой
 * плюс набор специфичных для типа атрибутов.
 *
 * Новая фича (например, "island" — островок безопасности) = новый класс
 * extends MapObjectEntity + свои специфичные поля.
 */
@MappedSuperclass
@Getter
@Setter
public abstract class MapObjectEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(columnDefinition = "geometry(Point,4326)", nullable = false)
    private Point location;

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
    }
}
