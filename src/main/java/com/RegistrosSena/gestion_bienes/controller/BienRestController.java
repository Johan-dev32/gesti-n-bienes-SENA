package com.RegistrosSena.gestion_bienes.controller;

import com.RegistrosSena.gestion_bienes.dto.BienDTO;
import com.RegistrosSena.gestion_bienes.model.Bien;
import com.RegistrosSena.gestion_bienes.service.BienService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/bienes")
public class BienRestController {

    private final BienService bienService;

    public BienRestController(BienService bienService) {
        this.bienService = bienService;
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

            if (b.getInstructor() != null) {
                dto.setNombreInstructor(
                        b.getInstructor().getNombres() + " " +
                                b.getInstructor().getApellidos());
            }

            if (b.getCentro() != null) {
                dto.setNombreCentro(b.getCentro().getNombre());

                if (b.getCentro().getRegional() != null) {
                    dto.setNombreRegional(
                            b.getCentro().getRegional().getNombre());
                }
            }

            dto.setFechaAdquisicion(
                    b.getFechaAdquisicion() != null ? b.getFechaAdquisicion().toString() : null);

            dto.setExpiracion(
                    b.getExpiracion() != null ? b.getExpiracion().toString() : null);

            dto.setValor(
                    b.getValor() != null ? b.getValor().doubleValue() : null);

            return dto;

        }).toList();
    }

    @PostMapping
    public ResponseEntity<Bien> guardarBien(@RequestBody Bien bien) {
        Bien guardado = bienService.guardar(bien);
        return ResponseEntity.status(201).body(guardado);
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
        dto.setPlaca(bien.getPlaca());

        return ResponseEntity.ok(dto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Bien> actualizarBien(@PathVariable Long id, @RequestBody Bien bienDetails) {

        Bien bien = bienService.getBienById(id)
                .orElseThrow(() -> new RuntimeException("Bien no encontrado"));

        bien.setModelo(bienDetails.getModelo());
        bien.setPlaca(bienDetails.getPlaca());
        bien.setConsecutivo(bienDetails.getConsecutivo());

        Bien actualizado = bienService.guardar(bien);

        return ResponseEntity.ok(actualizado);
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
