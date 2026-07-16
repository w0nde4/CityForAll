package com.example.accessmap.point;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Слой доступа к данным. JpaRepository уже даёт save / findById / findAll / deleteById и др.
 *
 * Новые запросы добавляются здесь одним из способов:
 *  1. Производные методы по имени:
 *       List<PointEntity> findByCreatedBy(String createdBy);
 *  2. Явный JPQL/SQL через @Query (в т.ч. пространственные функции PostGIS):
 *       @Query(value = "SELECT * FROM points p WHERE ST_DWithin(p.location, " +
 *                      ":center, :radiusMeters, true)", nativeQuery = true)
 *       List<PointEntity> findWithinRadius(Point center, double radiusMeters);
 */
@Repository
public interface PointRepository extends JpaRepository<PointEntity, Long> {
}
