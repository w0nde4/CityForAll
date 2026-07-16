package com.example.accessmap.road;

import com.example.accessmap.common.exception.ResourceNotFoundException;
import com.example.accessmap.road.dto.CreateRoadRequest;
import com.example.accessmap.road.dto.RoadResponse;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class RoadServiceImpl implements RoadService {

    private final RoadRepository repository;
    private final RoadMapper mapper;

    public RoadServiceImpl(RoadRepository repository, RoadMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    @Transactional
    public RoadResponse create(CreateRoadRequest request) {
        RoadEntity entity = mapper.toEntity(request);
        RoadEntity saved = repository.save(entity);
        return mapper.toResponse(saved);
    }

    @Override
    public RoadResponse getById(Long id) {
        return repository.findById(id)
                .map(mapper::toResponse)
                .orElseThrow(() -> new ResourceNotFoundException("Road", id));
    }

    @Override
    public List<RoadResponse> getAll() {
        return repository.findAll().stream()
                .map(mapper::toResponse)
                .toList();
    }

    @Override
    @Transactional
    public void delete(Long id) {
        if (!repository.existsById(id)) {
            throw new ResourceNotFoundException("Road", id);
        }
        repository.deleteById(id);
    }
}
