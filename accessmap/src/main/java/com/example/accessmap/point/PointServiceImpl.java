package com.example.accessmap.point;

import com.example.accessmap.common.exception.ResourceNotFoundException;
import com.example.accessmap.point.dto.CreatePointRequest;
import com.example.accessmap.point.dto.PointResponse;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Реализация бизнес-логики. Здесь живут правила, оркестрация репозитория и маппинга.
 * Контроллер не должен знать ни про сущности, ни про репозиторий — только про этот сервис.
 */
@Service
@Transactional(readOnly = true) // по умолчанию read-only; пишущие методы помечаем явно
public class PointServiceImpl implements PointService {

    private final PointRepository repository;
    private final PointMapper mapper;

    // Внедрение зависимостей через конструктор — предпочтительно (final-поля, удобно тестировать).
    public PointServiceImpl(PointRepository repository, PointMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    @Transactional
    public PointResponse create(CreatePointRequest request) {
        // Место для бизнес-валидации/обогащения: нормализация атрибутов,
        // проставление created_by из контекста безопасности и т.п.
        PointEntity entity = mapper.toEntity(request);
        PointEntity saved = repository.save(entity);
        return mapper.toResponse(saved);
    }

    @Override
    public PointResponse getById(Long id) {
        return repository.findById(id)
                .map(mapper::toResponse)
                .orElseThrow(() -> new ResourceNotFoundException("Point", id));
    }

    @Override
    public List<PointResponse> getAll() {
        // На реальном объёме замени на постраничную выдачу (Pageable) и/или
        // выборку по bbox/радиусу — фронт обычно грузит точки в видимой области.
        return repository.findAll().stream()
                .map(mapper::toResponse)
                .toList();
    }

    @Override
    @Transactional
    public void delete(Long id) {
        if (!repository.existsById(id)) {
            throw new ResourceNotFoundException("Point", id);
        }
        repository.deleteById(id);
    }
}
