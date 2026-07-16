package com.example.accessmap.point;

import com.example.accessmap.point.dto.CreatePointRequest;
import com.example.accessmap.point.dto.PointResponse;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Handler-слой (вход в приложение по HTTP).
 * Задача максимально тонкая: принять запрос, провалидировать формат (@Valid),
 * делегировать сервису, вернуть ответ + корректный HTTP-статус.
 * Никакой бизнес-логики здесь быть не должно.
 */
@RestController
@RequestMapping("/api/v1/points")
public class PointController {

    private final PointService service;

    public PointController(PointService service) {
        this.service = service;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public PointResponse create(@Valid @RequestBody CreatePointRequest request) {
        return service.create(request);
    }

    @GetMapping("/{id}")
    public PointResponse getById(@PathVariable Long id) {
        return service.getById(id);
    }

    @GetMapping
    public List<PointResponse> getAll() {
        return service.getAll();
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}
