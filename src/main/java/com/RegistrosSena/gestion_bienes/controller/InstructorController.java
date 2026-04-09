package com.RegistrosSena.gestion_bienes.controller;

import com.RegistrosSena.gestion_bienes.dto.InstructorDTO;
import com.RegistrosSena.gestion_bienes.model.Instructor;
import com.RegistrosSena.gestion_bienes.service.InstructorService;

import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/instructores")
public class InstructorController {

    @Autowired
    private InstructorService instructorService;

    @GetMapping
    public ResponseEntity<List<Instructor>> listar() {
        return ResponseEntity.ok(instructorService.getAllInstructores());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Instructor> obtener(@PathVariable Long id) {
        return instructorService.getInstructorById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Instructor> crear(@Valid @RequestBody InstructorDTO dto) {

        Instructor instructor = new Instructor();
        instructor.setNombres(dto.getNombres());
        instructor.setApellidos(dto.getApellidos());
        instructor.setCorreo(dto.getCorreo());
        instructor.setCelular(dto.getCelular());

        Instructor guardado = instructorService.createInstructor(instructor);

        return ResponseEntity.status(HttpStatus.CREATED).body(guardado);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Instructor> actualizar(@PathVariable Long id,
            @Valid @RequestBody InstructorDTO dto) {
        try {
            Instructor instructor = new Instructor();
            instructor.setNombres(dto.getNombres());
            instructor.setApellidos(dto.getApellidos());
            instructor.setCorreo(dto.getCorreo());
            instructor.setCelular(dto.getCelular());

            Instructor actualizado = instructorService.updateInstructor(id, instructor);

            return ResponseEntity.ok(actualizado);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        try {
            instructorService.deleteInstructor(id);
            return ResponseEntity.noContent().build(); // 204
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
}