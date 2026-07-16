package com.example.accessmap.road;

import com.example.accessmap.road.dto.CreateRoadRequest;
import com.example.accessmap.road.dto.RoadResponse;

import java.util.List;

public interface RoadService {

    RoadResponse create(CreateRoadRequest request);

    RoadResponse getById(Long id);

    List<RoadResponse> getAll();

    void delete(Long id);
}
