package com.example.accessmap.road;

import com.example.accessmap.common.entity.MapObjectEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

/** Дорога/участок пути. Зеркало таблицы `road` из псевдоБД + общие поля MapObjectEntity. */
@Entity
@Table(name = "road")
@Getter
@Setter
public class RoadEntity extends MapObjectEntity {

    @Column(name = "traffic_lanes", nullable = false)
    private Integer trafficLanes;

    /** FK на road_surface. */
    @Column(name = "surface_quality", nullable = false)
    private Long surfaceQuality;

    @Column(name = "tram_tracks", nullable = false)
    private boolean tramTracks;

    /** Условное поле: null, если tram_tracks == false. */
    @Column(name = "rail_height")
    private Double railHeight;

    @Column(name = "traffic_light", nullable = false)
    private boolean trafficLight;

    /** Условное поле: null, если traffic_light == false. */
    @Column(name = "audible_traffic_light")
    private Boolean audibleTrafficLight;

    /** Условное поле: null, если traffic_light == false. */
    @Column(name = "countdown_timer")
    private Boolean countdownTimer;
}
