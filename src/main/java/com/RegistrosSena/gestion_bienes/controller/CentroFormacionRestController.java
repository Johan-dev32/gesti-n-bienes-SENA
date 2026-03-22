package com.RegistrosSena.gestion_bienes.controller;

import com.RegistrosSena.gestion_bienes.model.CentroFormacion;
import com.RegistrosSena.gestion_bienes.service.CentroFormacionService;
import com.RegistrosSena.gestion_bienes.dto.CentroDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/centros")
public class CentroFormacionRestController {

    private final CentroFormacionService service;

    public CentroFormacionRestController(CentroFormacionService service) {
        this.service = service;
    }

    @GetMapping
    public List<CentroDTO> listarCentros() {

        return service.getAllCentros().stream().map(c -> {
            CentroDTO dto = new CentroDTO();

            dto.setId(c.getId());
            dto.setNombre(c.getNombre());
            dto.setCodigo(c.getCodigo());

            if (c.getRegional() != null) {
                dto.setNombreRegional(c.getRegional().getNombre());
            }

            return dto;
        }).toList();
    }

    @PostMapping
    public ResponseEntity<CentroDTO> guardarCentro(@RequestBody CentroFormacion centro) {

        service.guardar(centro);

        CentroDTO dto = new CentroDTO();
        dto.setNombre(centro.getNombre());
        dto.setCodigo(centro.getCodigo());

        return ResponseEntity.status(201).body(dto);
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
            dto.setNombreRegional(centro.getRegional().getNombre());
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
            @RequestBody CentroFormacion centroActualizado) {

        CentroFormacion existente = service.getCentroById(id);

        if (existente == null) {
            return ResponseEntity.notFound().build();
        }

        existente.setNombre(centroActualizado.getNombre());
        existente.setCodigo(centroActualizado.getCodigo());

        CentroFormacion actualizado = service.guardar(existente);

        CentroDTO dto = new CentroDTO();
        dto.setId(actualizado.getId());
        dto.setNombre(actualizado.getNombre());

        return ResponseEntity.ok(dto);
    }
}
