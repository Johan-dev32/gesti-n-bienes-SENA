package com.RegistrosSena.gestion_bienes.controller;

import com.RegistrosSena.gestion_bienes.dto.BienDTO;
import com.RegistrosSena.gestion_bienes.model.Bien;
import com.RegistrosSena.gestion_bienes.model.CentroFormacion;
import com.RegistrosSena.gestion_bienes.model.Instructor;
import com.RegistrosSena.gestion_bienes.service.BienService;
import com.RegistrosSena.gestion_bienes.service.CentroFormacionService;
import com.RegistrosSena.gestion_bienes.service.InstructorService;

import jakarta.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/bienes")
public class BienRestController {

    private final BienService bienService;
    private final InstructorService instructorService;
    private final CentroFormacionService centroService;

    public BienRestController(BienService bienService,
            InstructorService instructorService,
            CentroFormacionService centroService) {
        this.bienService = bienService;
        this.instructorService = instructorService;
        this.centroService = centroService;
    }

    @GetMapping
    public List<BienDTO> listar() {

        return bienService.getAllBienes().stream().map(b -> {

            BienDTO dto = new BienDTO();

            dto.setId(b.getId());
            dto.setModelo(b.getModelo());
            dto.setConsecutivo(b.getConsecutivo());
            dto.setPlaca(b.getPlaca());
            dto.setDescripcion(b.getDescripcion());
            dto.setAtributos(b.getAtributos());
            dto.setElementoDescripcion(b.getElementoDescripcion());
            dto.setFechaAdquisicion(b.getFechaAdquisicion());
            dto.setExpiracion(b.getExpiracion());
            dto.setValor(b.getValor());

            if (b.getInstructor() != null) {
                dto.setIdInstructor(b.getInstructor().getId());
            }

            if (b.getCentro() != null) {
                dto.setIdCentro(b.getCentro().getId());
            }

            return dto;

        }).toList();
    }

    @PostMapping
    public ResponseEntity<BienDTO> guardarBien(@Valid @RequestBody BienDTO dto) {

        Instructor instructor = instructorService.getInstructorById(dto.getIdInstructor())
                .orElseThrow(() -> new RuntimeException("Instructor no encontrado"));

        CentroFormacion centro = centroService.getCentroById(dto.getIdCentro());

        if (centro == null) {
            throw new RuntimeException("Centro no encontrado");
        }

        Bien bien = new Bien();
        bien.setModelo(dto.getModelo());
        bien.setConsecutivo(dto.getConsecutivo());
        bien.setPlaca(dto.getPlaca());
        bien.setDescripcion(dto.getDescripcion());
        bien.setAtributos(dto.getAtributos());
        bien.setElementoDescripcion(dto.getElementoDescripcion());
        bien.setFechaAdquisicion(dto.getFechaAdquisicion());
        bien.setExpiracion(dto.getExpiracion());
        bien.setValor(dto.getValor());

        bien.setInstructor(instructor);
        bien.setCentro(centro);

        Bien guardado = bienService.guardar(bien);

        BienDTO response = new BienDTO();
        response.setId(guardado.getId());
        response.setModelo(guardado.getModelo());
        response.setIdInstructor(instructor.getId());
        response.setIdCentro(centro.getId());

        return ResponseEntity.status(201).body(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<BienDTO> obtener(@PathVariable Long id) {

        Bien bien = bienService.getBienById(id).orElse(null);

        if (bien == null) {
            return ResponseEntity.notFound().build();
        }

        BienDTO dto = new BienDTO();
        dto.setId(bien.getId());
        dto.setModelo(bien.getModelo());

        if (bien.getInstructor() != null) {
            dto.setIdInstructor(bien.getInstructor().getId());
        }

        if (bien.getCentro() != null) {
            dto.setIdCentro(bien.getCentro().getId());
        }

        return ResponseEntity.ok(dto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<BienDTO> actualizarBien(
            @PathVariable Long id,
            @Valid @RequestBody BienDTO dto) {

        Bien bien = bienService.getBienById(id)
                .orElseThrow(() -> new RuntimeException("Bien no encontrado"));

        Instructor instructor = instructorService.getInstructorById(dto.getIdInstructor())
                .orElseThrow(() -> new RuntimeException("Instructor no encontrado"));

        CentroFormacion centro = centroService.getCentroById(dto.getIdCentro());

        if (centro == null) {
            throw new RuntimeException("Centro no encontrado");
        }

        bien.setModelo(dto.getModelo());
        bien.setConsecutivo(dto.getConsecutivo());
        bien.setPlaca(dto.getPlaca());
        bien.setDescripcion(dto.getDescripcion());
        bien.setAtributos(dto.getAtributos());
        bien.setElementoDescripcion(dto.getElementoDescripcion());
        bien.setFechaAdquisicion(dto.getFechaAdquisicion());
        bien.setExpiracion(dto.getExpiracion());
        bien.setValor(dto.getValor());

        bien.setInstructor(instructor);
        bien.setCentro(centro);

        Bien actualizado = bienService.guardar(bien);

        BienDTO response = new BienDTO();
        response.setId(actualizado.getId());
        response.setModelo(actualizado.getModelo());
        response.setIdInstructor(instructor.getId());
        response.setIdCentro(centro.getId());

        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarBien(@PathVariable Long id) {

        Bien bien = bienService.getBienById(id).orElse(null);

        if (bien == null) {
            return ResponseEntity.notFound().build();
        }

        bienService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}