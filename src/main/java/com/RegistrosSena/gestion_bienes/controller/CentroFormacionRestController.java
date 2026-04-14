package com.RegistrosSena.gestion_bienes.controller;

import com.RegistrosSena.gestion_bienes.model.CentroFormacion;
import com.RegistrosSena.gestion_bienes.model.Regional;
import com.RegistrosSena.gestion_bienes.service.CentroFormacionService;
import com.RegistrosSena.gestion_bienes.service.RegionalService;
import com.RegistrosSena.gestion_bienes.dto.CentroDTO;

import jakarta.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/centros")
public class CentroFormacionRestController {

    private final CentroFormacionService service;
    private final RegionalService regionalService;

    public CentroFormacionRestController(CentroFormacionService service,
            RegionalService regionalService) {
        this.service = service;
        this.regionalService = regionalService;
    }

    @GetMapping
    public List<CentroDTO> listarCentros() {

        return service.getAllCentros().stream().map(c -> {
            CentroDTO dto = new CentroDTO();

            dto.setId(c.getId());
            dto.setNombre(c.getNombre());
            dto.setCodigo(c.getCodigo());

            if (c.getRegional() != null) {
                dto.setIdRegional(c.getRegional().getId());
            }

            return dto;
        }).toList();
    }

    @PostMapping
    public ResponseEntity<CentroDTO> guardarCentro(@Valid @RequestBody CentroDTO dto) {

        Regional regional = regionalService.getRegionalById(dto.getIdRegional())
                .orElseThrow(() -> new RuntimeException("Regional no encontrada"));

        CentroFormacion centro = new CentroFormacion();
        centro.setNombre(dto.getNombre());
        centro.setCodigo(dto.getCodigo());
        centro.setRegional(regional);

        CentroFormacion guardado = service.guardar(centro);

        CentroDTO response = new CentroDTO();
        response.setId(guardado.getId());
        response.setNombre(guardado.getNombre());
        response.setCodigo(guardado.getCodigo());
        response.setIdRegional(regional.getId());

        return ResponseEntity.status(201).body(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CentroDTO> obtenerPorId(@PathVariable Long id) {

        CentroFormacion centro = service.getCentroById(id);

        if (centro == null) {
            return ResponseEntity.notFound().build();
        }

        CentroDTO dto = new CentroDTO();
        dto.setId(centro.getId());
        dto.setNombre(centro.getNombre());
        dto.setCodigo(centro.getCodigo());

        if (centro.getRegional() != null) {
            dto.setIdRegional(centro.getRegional().getId());
        }

        return ResponseEntity.ok(dto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {

        CentroFormacion centro = service.getCentroById(id);

        if (centro == null) {
            return ResponseEntity.notFound().build();
        }

        service.eliminar(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<CentroDTO> actualizar(
            @PathVariable Long id,
            @Valid @RequestBody CentroDTO dto) {

        CentroFormacion existente = service.getCentroById(id);

        if (existente == null) {
            return ResponseEntity.notFound().build();
        }

        Regional regional = regionalService.getRegionalById(dto.getIdRegional())
                .orElseThrow(() -> new RuntimeException("Regional no encontrada"));

        existente.setNombre(dto.getNombre());
        existente.setCodigo(dto.getCodigo());
        existente.setRegional(regional);

        CentroFormacion actualizado = service.guardar(existente);

        CentroDTO response = new CentroDTO();
        response.setId(actualizado.getId());
        response.setNombre(actualizado.getNombre());
        response.setCodigo(actualizado.getCodigo());
        response.setIdRegional(regional.getId());

        return ResponseEntity.ok(response);
    }
}