package com.example.accessmap.common.exception;

import java.time.Instant;
import java.util.Map;

/**
 * Единый формат тела ошибки для всего API.
 *
 * @param timestamp когда произошло
 * @param status    HTTP-код
 * @param error     краткое имя ошибки
 * @param message   человекочитаемое описание
 * @param fieldErrors ошибки валидации по полям (может быть null)
 */
public record ApiError(
        Instant timestamp,
        int status,
        String error,
        String message,
        Map<String, String> fieldErrors
) {
    public static ApiError of(int status, String error, String message) {
        return new ApiError(Instant.now(), status, error, message, null);
    }

    public static ApiError of(int status, String error, String message, Map<String, String> fieldErrors) {
        return new ApiError(Instant.now(), status, error, message, fieldErrors);
    }
}
