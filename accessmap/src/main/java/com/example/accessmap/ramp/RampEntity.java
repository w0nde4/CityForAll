package com.example.accessmap.ramp;

import com.example.accessmap.common.entity.MapObjectEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

/**
 * Съезд. Зеркало таблицы `ramp` из псевдоБД + общие поля MapObjectEntity
 * (location, createdAt, createdBy).
 *
 * Поля точно совпадают с RampEntity на фронте (ramp.entity.ts) —
 * это сознательно: контракт фронта уже написан, бэк подстраивается под него.
 */
@Entity
@Table(name = "ramp")
@Getter
@Setter
public class RampEntity extends MapObjectEntity {

    /** FK на curb_height. Фронт уже присылает готовый числовой id (см. RampParser). */
    @Column(name = "curb_height", nullable = false)
    private Long curbHeight;

    @Column(name = "road_access", nullable = false)
    private boolean roadAccess;

    @Column(name = "walker_barrier", nullable = false)
    private boolean walkerBarrier;

    @Column(name = "tactile_tile", nullable = false)
    private boolean tactileTile;

    /** Условное поле: null, если road_access == false (правило задано на фронте). */
    @Column(name = "ramp_width")
    private Double rampWidth;

    /** Условное поле: null, если road_access == false. */
    @Column(name = "angle_slant")
    private Double angleSlant;
}
