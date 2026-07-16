package com.example.accessmap.crossing.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;

/**
 * Тело POST /api/crossings. platform_working не помечено @NotNull — условное
 * поле, null при lifting_platform == false (см. README фронта).
 *
 * ВНИМАНИЕ: handrailsBelow70cm — единственное поле во всём проекте, где
 * автоматическая SNAKE_CASE-конвертация Jackson даёт неверный результат
 * (цифра "70" внутри имени превращается в "below70cm" без подчёркивания).
 * Поэтому здесь и в CrossingResponse имя JSON-поля прописано явно.
 */
public record CreateCrossingRequest(

        @NotNull @DecimalMin("-90.0") @DecimalMax("90.0")
        Double latitude,

        @NotNull @DecimalMin("-180.0") @DecimalMax("180.0")
        Double longitude,

        @NotNull
        Long entranceType,

        @NotNull
        Long crossingType,

        boolean ramp,
        boolean liftingPlatform,
        Boolean platformWorking,

        @JsonProperty("handrails_below_70cm")
        boolean handrailsBelow70cm,

        boolean stairContrastMarking,
        boolean tactileStrip,
        boolean lighting,

        @NotNull
        Long tactileTileCondition
) {
}
