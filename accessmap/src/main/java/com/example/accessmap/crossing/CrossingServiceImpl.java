package com.example.accessmap.crossing;

import com.example.accessmap.common.exception.ResourceNotFoundException;
import com.example.accessmap.crossing.dto.CreateCrossingRequest;
import com.example.accessmap.crossing.dto.CrossingResponse;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class CrossingServiceImpl implements CrossingService {

    private final CrossingRepository repository;
    private final CrossingMapper mapper;

    public CrossingServiceImpl(CrossingRepository repository, CrossingMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    @Transactional
    public CrossingResponse create(CreateCrossingRequest request) {
        CrossingEntity entity = mapper.toEntity(request);
        CrossingEntity saved = repository.save(entity);
        return mapper.toResponse(saved);
    }

    @Override
    public CrossingResponse getById(Long id) {
        return repository.findById(id)
                .map(mapper::toResponse)
                .orElseThrow(() -> new ResourceNotFoundException("Crossing", id));
    }

    @Override
    public List<CrossingResponse> getAll() {
        return repository.findAll().stream()
                .map(mapper::toResponse)
                .toList();
    }

    @Override
    @Transactional
    public void delete(Long id) {
        if (!repository.existsById(id)) {
            throw new ResourceNotFoundException("Crossing", id);
        }
        repository.deleteById(id);
    }
}
