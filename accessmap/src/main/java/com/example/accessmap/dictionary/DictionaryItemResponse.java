package com.example.accessmap.dictionary;

/**
 * Ответ для любого справочника. Форма совпадает с DictionaryItem на фронте
 * (dictionary-service.ts): { id, variant }.
 */
public record DictionaryItemResponse(Long id, String variant) {
}
