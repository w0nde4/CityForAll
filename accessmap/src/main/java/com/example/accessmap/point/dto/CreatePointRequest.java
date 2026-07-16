package com.example.accessmap.point.dto;

import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;

import java.util.Map;

/**
 * Тело запроса при создании точки (то, что присылает фронтенд, когда
 * пользователь кликнул по карте и заполнил данные).
 *
 * DTO намеренно отделён от сущности: API-контракт можно менять независимо
 * от схемы БД, а наружу не утекают детали хранения.
 *
 * @param latitude  широта  [-90; 90]
 * @param longitude долгота [-180; 180]
 * @param attributes произвольные дорожные атрибуты (ширина, бордюр, покрытие, ...)
 */
public record CreatePointRequest(

        @NotNull
        @DecimalMin(value = "-90.0")
        @DecimalMax(value = "90.0")
        Double latitude,

        @NotNull
        @DecimalMin(value = "-180.0")
        @DecimalMax(value = "180.0")
        Double longitude,

        Map<String, Object> attributes
) {
}
