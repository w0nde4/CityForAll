package com.example.accessmap.dictionary;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

/** Справочник качества дорожного покрытия. Используется в road.surface_quality. */
@Entity
@Table(name = "road_surface")
public class RoadSurfaceVariant extends AbstractDictionaryEntity {
}
