package com.example.accessmap.dictionary;

import com.example.accessmap.common.exception.ResourceNotFoundException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Один контроллер обслуживает ВСЕ справочники — путь совпадает с тем, что
 * ждёт фронт: baseUrl "/api" + "/dictionaries/curb-height" (см.
 * CURB_HEIGHT_ENDPOINT в ramp.dictionary.ts). Для нового справочника
 * отдельный контроллер писать не нужно — см. DictionaryTypeRegistry.
 */
@RestController
@RequestMapping("/api/dictionaries")
public class DictionaryController {

    private final DictionaryTypeRegistry registry;

    public DictionaryController(DictionaryTypeRegistry registry) {
        this.registry = registry;
    }

    @GetMapping("/{slug}")
    public List<DictionaryItemResponse> getBySlug(@PathVariable String slug) {
        DictionaryRepository<? extends AbstractDictionaryEntity> repository = registry.find(slug)
                .orElseThrow(() -> new ResourceNotFoundException("Dictionary", slug));

        return repository.findAll().stream()
                .map(item -> new DictionaryItemResponse(item.getId(), item.getVariant()))
                .toList();
    }
}
