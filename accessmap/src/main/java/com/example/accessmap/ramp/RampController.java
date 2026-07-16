package com.example.accessmap.ramp;

import com.example.accessmap.ramp.dto.CreateRampRequest;
import com.example.accessmap.ramp.dto.RampResponse;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Путь "/api/ramps" — совпадает с тем, что уже зашито во фронте:
 * baseUrl "/api" + RAMP_ENDPOINT "/ramps" (ramp.repository.ts).
 */
@RestController
@RequestMapping("/api/ramps")
public class RampController {

    private final RampService service;

    public RampController(RampService service) {
        this.service = service;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public RampResponse create(@Valid @RequestBody CreateRampRequest request) {
        return service.create(request);
    }

    @GetMapping("/{id}")
    public RampResponse getById(@PathVariable Long id) {
        return service.getById(id);
    }

    @GetMapping
    public List<RampResponse> getAll() {
        return service.getAll();
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}
