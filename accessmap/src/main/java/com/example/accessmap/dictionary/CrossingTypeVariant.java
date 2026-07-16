package com.example.accessmap.dictionary;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

/** Справочник типа перехода (underground / overground). Используется в crossing.crossing_type. */
@Entity
@Table(name = "crossing_type")
public class CrossingTypeVariant extends AbstractDictionaryEntity {
}
