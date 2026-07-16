package com.example.accessmap.ramp.dto;

import java.time.Instant;

/**
 * Ответ наружу. Фронт (RampCreatedResponse) сейчас читает только id,
 * но остальные поля пригодятся для будущих экранов (список точек на карте,
 * история правок) — дешевле отдать сразу, чем потом заводить второй эндпоинт.
 */
public record RampResponse(
        Long id,
        double latitude,
        double longitude,
        Long curbHeight,
        boolean roadAccess,
        boolean walkerBarrier,
        boolean tactileTile,
        Double rampWidth,
        Double angleSlant,
        Instant createdAt,
        String createdBy
) {
}
