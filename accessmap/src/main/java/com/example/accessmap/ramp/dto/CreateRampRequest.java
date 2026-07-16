package com.example.accessmap.ramp.dto;

import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;

/**
 * Тело POST /api/ramps. Поля 1-в-1 совпадают с RampEntity на фронте
 * (curb_height, road_access, walker_barrier, tactile_tile, ramp_width,
 * angle_slant — благодаря SNAKE_CASE в application.yml). Плюс координата,
 * которой в псевдоБД не было (см. комментарий в MapObjectEntity).
 *
 * ramp_width/angle_slant не помечены @NotNull: они условные и приходят
 * null, если road_access == false — фронт это уже гарантирует (см.
 * RampParser), поэтому здесь мы им доверяем и просто принимаем как есть.
 * ТОЧКА РАСШИРЕНИЯ: если понадобится подстраховаться от кривого клиента —
 * сюда добавляется кросс-валидация (например, через @AssertTrue-метод).
 */
public record CreateRampRequest(

        @NotNull @DecimalMin("-90.0") @DecimalMax("90.0")
        Double latitude,

        @NotNull @DecimalMin("-180.0") @DecimalMax("180.0")
        Double longitude,

        @NotNull
        Long curbHeight,

        boolean roadAccess,
        boolean walkerBarrier,
        boolean tactileTile,

        Double rampWidth,
        Double angleSlant
) {
}
