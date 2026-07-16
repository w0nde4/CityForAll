package com.example.accessmap.crossing;

import com.example.accessmap.common.entity.MapObjectEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

/** Пешеходный переход. Общие поля MapObjectEntity. */
@Entity
@Table(name = "crossing")
@Getter
@Setter
public class CrossingEntity extends MapObjectEntity {

    /** FK на entrance_type. */
    @Column(name = "entrance_type", nullable = false)
    private Long entranceType;

    /** FK на crossing_type. */
    @Column(name = "crossing_type", nullable = false)
    private Long crossingType;

    @Column(name = "ramp", nullable = false)
    private boolean ramp;

    @Column(name = "lifting_platform", nullable = false)
    private boolean liftingPlatform;

    /** Условное поле: null, если lifting_platform == false. */
    @Column(name = "platform_working")
    private Boolean platformWorking;

    @Column(name = "handrails_below_70cm", nullable = false)
    private boolean handrailsBelow70cm;

    @Column(name = "stair_contrast_marking", nullable = false)
    private boolean stairContrastMarking;

    @Column(name = "tactile_strip", nullable = false)
    private boolean tactileStrip;

    @Column(name = "lighting", nullable = false)
    private boolean lighting;

    /** FK на tactile_tile_condition. */
    @Column(name = "tactile_tile_condition", nullable = false)
    private Long tactileTileCondition;
}
