package com.example.accessmap.point.dto;

import java.time.Instant;
import java.util.Map;

/**
 * Представление точки, отдаваемое наружу. Координаты разложены на широту/долготу,
 * чтобы фронтенду не приходилось парсить геометрию.
 */
public record PointResponse(
        Long id,
        double latitude,
        double longitude,
        Map<String, Object> attributes,
        Instant createdAt,
        String createdBy
) {
}
