package com.example.accessmap.common.exception;

/**
 * Бросается, когда сущность не найдена. Глобальный обработчик превращает её в 404.
 */
public class ResourceNotFoundException extends RuntimeException {

    public ResourceNotFoundException(String resource, Object id) {
        super("%s with id %s not found".formatted(resource, id));
    }
}
