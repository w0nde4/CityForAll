package com.example.accessmap.dictionary;

import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 * Связывает slug из URL (как его ждёт фронт, см. ramp.dictionary.ts:
 * "/dictionaries/curb-height") с конкретным репозиторием справочника.
 *
 * ТОЧКА РАСШИРЕНИЯ: появился новый справочник (entity + repository, как
 * CurbHeightVariant/CurbHeightRepository выше) — добавляешь ОДНУ строку
 * в map ниже. Контроллер и остальная инфраструктура не меняются.
 */
@Component
public class DictionaryTypeRegistry {

    private final Map<String, DictionaryRepository<? extends AbstractDictionaryEntity>> repositoriesBySlug;

    public DictionaryTypeRegistry(
            CurbHeightRepository curbHeight,
            RoadSurfaceRepository roadSurface,
            EntranceTypeRepository entranceType,
            CrossingTypeRepository crossingType,
            TactileTileConditionRepository tactileTileCondition
    ) {
        // HashMap + put(), а не Map.of(...): у значений разные конкретные
        // параметризации generic-типа, и через put() это собирается без
        // сюрпризов с выводом типов.
        Map<String, DictionaryRepository<? extends AbstractDictionaryEntity>> repos = new HashMap<>();
        repos.put("curb-height", curbHeight);
        repos.put("road-surface", roadSurface);
        repos.put("entrance-type", entranceType);
        repos.put("crossing-type", crossingType);
        repos.put("tactile-tile-condition", tactileTileCondition);
        this.repositoriesBySlug = repos;
    }

    public Optional<DictionaryRepository<? extends AbstractDictionaryEntity>> find(String slug) {
        return Optional.ofNullable(repositoriesBySlug.get(slug));
    }
}
