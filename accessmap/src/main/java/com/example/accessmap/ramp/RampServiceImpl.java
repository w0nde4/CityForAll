package com.example.accessmap.ramp;

import com.example.accessmap.common.exception.ResourceNotFoundException;
import com.example.accessmap.ramp.dto.CreateRampRequest;
import com.example.accessmap.ramp.dto.RampResponse;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class RampServiceImpl implements RampService {

    private final RampRepository repository;
    private final RampMapper mapper;

    public RampServiceImpl(RampRepository repository, RampMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    @Transactional
    public RampResponse create(CreateRampRequest request) {
        RampEntity entity = mapper.toEntity(request);
        RampEntity saved = repository.save(entity);
        return mapper.toResponse(saved);
    }

    @Override
    public RampResponse getById(Long id) {
        return repository.findById(id)
                .map(mapper::toResponse)
                .orElseThrow(() -> new ResourceNotFoundException("Ramp", id));
    }

    @Override
    public List<RampResponse> getAll() {
        return repository.findAll().stream()
                .map(mapper::toResponse)
                .toList();
    }

    @Override
    @Transactional
    public void delete(Long id) {
        if (!repository.existsById(id)) {
            throw new ResourceNotFoundException("Ramp", id);
        }
        repository.deleteById(id);
    }
}
