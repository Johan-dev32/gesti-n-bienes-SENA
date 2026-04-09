package com.RegistrosSena.gestion_bienes.controller;

import com.RegistrosSena.gestion_bienes.dto.RegionalDTO;
import com.RegistrosSena.gestion_bienes.model.Regional;
import com.RegistrosSena.gestion_bienes.service.RegionalService;

import jakarta.validation.Valid;

import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/regionales")
public class RegionalRestController {

    private final RegionalService service;

    public RegionalRestController(RegionalService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<Regional>> listar() {
        return ResponseEntity.ok(service.listar());
    }

    @PostMapping
    public ResponseEntity<Regional> crear(@Valid @RequestBody RegionalDTO dto) {

        Regional regional = new Regional();
        regional.setCodigo(dto.getCodigo());
        regional.setNombre(dto.getNombre());

        Regional guardado = service.guardar(regional);

        return ResponseEntity.status(HttpStatus.CREATED).body(guardado);
    }
}