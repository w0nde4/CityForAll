package com.example.accessmap.dictionary;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

/** Справочник состояния тактильной плитки. Используется в crossing.tactile_tile_condition. */
@Entity
@Table(name = "tactile_tile_condition")
public class TactileTileConditionVariant extends AbstractDictionaryEntity {
}
