package com.example.accessmap.point;

import com.example.accessmap.point.dto.CreatePointRequest;
import com.example.accessmap.point.dto.PointResponse;

import java.util.List;

/**
 * Бизнес-слой. Интерфейс отделён от реализации:
 *  - легко мокать в тестах контроллера;
 *  - можно подменить реализацию, не трогая контроллер.
 *
 * Новая операция = новый метод здесь + его реализация в {@link PointServiceImpl}.
 */
public interface PointService {

    PointResponse create(CreatePointRequest request);

    PointResponse getById(Long id);

    List<PointResponse> getAll();

    void delete(Long id);
}
