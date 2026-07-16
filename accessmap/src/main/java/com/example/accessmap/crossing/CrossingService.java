package com.example.accessmap.crossing;

import com.example.accessmap.crossing.dto.CreateCrossingRequest;
import com.example.accessmap.crossing.dto.CrossingResponse;

import java.util.List;

public interface CrossingService {

    CrossingResponse create(CreateCrossingRequest request);

    CrossingResponse getById(Long id);

    List<CrossingResponse> getAll();

    void delete(Long id);
}
