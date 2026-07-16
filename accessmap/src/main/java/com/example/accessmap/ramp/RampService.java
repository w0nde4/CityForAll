package com.example.accessmap.ramp;

import com.example.accessmap.ramp.dto.CreateRampRequest;
import com.example.accessmap.ramp.dto.RampResponse;

import java.util.List;

public interface RampService {

    RampResponse create(CreateRampRequest request);

    RampResponse getById(Long id);

    List<RampResponse> getAll();

    void delete(Long id);
}
