package com.example.accessmap.dictionary;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

/** Справочник типа входа (covered / open air). Используется в crossing.entrance_type. */
@Entity
@Table(name = "entrance_type")
public class EntranceTypeVariant extends AbstractDictionaryEntity {
}
