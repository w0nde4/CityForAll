package com.example.accessmap.ramp;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Новые запросы (например, "все съезды в bbox видимой области карты")
 * добавляются сюда — производным методом или @Query с ST_-функциями
 * PostGIS. См. комментарий в старом шаблоне PointRepository для примеров.
 */
@Repository
public interface RampRepository extends JpaRepository<RampEntity, Long> {
}
