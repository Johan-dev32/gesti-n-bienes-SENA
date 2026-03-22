package com.RegistrosSena.gestion_bienes.controller;

import com.RegistrosSena.gestion_bienes.model.Regional;
import com.RegistrosSena.gestion_bienes.repository.RegionalRepository;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/regionales")
public class RegionalRestController {

    private final RegionalRepository regionalRepository;

    public RegionalRestController(RegionalRepository regionalRepository) {
        this.regionalRepository = regionalRepository;
    }

    @GetMapping
    public List<Regional> listarRegionales() {
        return regionalRepository.findAll();
    }

    @PostMapping
    public Regional guardarRegional(@RequestBody Regional regional) {
        return regionalRepository.save(regional);
    }
}