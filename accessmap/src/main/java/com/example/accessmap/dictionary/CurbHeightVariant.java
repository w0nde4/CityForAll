package com.example.accessmap.dictionary;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

/** Справочник высоты бордюра: {"<2cm", "2-4cm", "4-5cm", ">5cm"}. Используется в ramp.curb_height. */
@Entity
@Table(name = "curb_height")
public class CurbHeightVariant extends AbstractDictionaryEntity {
}
