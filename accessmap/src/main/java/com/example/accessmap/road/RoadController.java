package com.example.accessmap.road;

import com.example.accessmap.road.dto.CreateRoadRequest;
import com.example.accessmap.road.dto.RoadResponse;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/roads")
public class RoadController {

    private final RoadService service;

    public RoadController(RoadService service) {
        this.service = service;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public RoadResponse create(@Valid @RequestBody CreateRoadRequest request) {
        return service.create(request);
    }

    @GetMapping("/{id}")
    public RoadResponse getById(@PathVariable Long id) {
        return service.getById(id);
    }

    @GetMapping
    public List<RoadResponse> getAll() {
        return service.getAll();
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}
