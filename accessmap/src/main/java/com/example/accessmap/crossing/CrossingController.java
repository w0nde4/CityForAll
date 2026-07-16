package com.example.accessmap.crossing;

import com.example.accessmap.crossing.dto.CreateCrossingRequest;
import com.example.accessmap.crossing.dto.CrossingResponse;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/crossings")
public class CrossingController {

    private final CrossingService service;

    public CrossingController(CrossingService service) {
        this.service = service;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CrossingResponse create(@Valid @RequestBody CreateCrossingRequest request) {
        return service.create(request);
    }

    @GetMapping("/{id}")
    public CrossingResponse getById(@PathVariable Long id) {
        return service.getById(id);
    }

    @GetMapping
    public List<CrossingResponse> getAll() {
        return service.getAll();
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}
