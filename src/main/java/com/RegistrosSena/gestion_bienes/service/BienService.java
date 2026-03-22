package com.RegistrosSena.gestion_bienes.service;

import com.RegistrosSena.gestion_bienes.model.Bien;
import com.RegistrosSena.gestion_bienes.model.Instructor;
import com.RegistrosSena.gestion_bienes.repository.BienRepository;
import com.RegistrosSena.gestion_bienes.repository.InstructorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BienService {

    @Autowired
    private BienRepository bienRepository;

    @Autowired
    private InstructorRepository instructorRepository;

    public Bien guardar(Bien bien) {

        if (bien.getInstructor() != null && bien.getInstructor().getId() != null) {
            Instructor instructor = instructorRepository.findById(bien.getInstructor().getId())
                    .orElseThrow(() -> new RuntimeException("Instructor no encontrado"));
            bien.setInstructor(instructor);
        }

        return bienRepository.save(bien);
    }

    public List<Bien> getAllBienes() {
        return bienRepository.findAll();
    }

    public Optional<Bien> getBienById(Long id) {
        return bienRepository.findById(id);
    }

    public Bien updateBien(Long id, Bien bienDetails) {

        Bien bien = bienRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Bien no encontrado con ID: " + id));

        bien.setModelo(bienDetails.getModelo());
        bien.setConsecutivo(bienDetails.getConsecutivo());
        bien.setElementoDescripcion(bienDetails.getElementoDescripcion());
        bien.setDescripcion(bienDetails.getDescripcion());
        bien.setPlaca(bienDetails.getPlaca());
        bien.setAtributos(bienDetails.getAtributos());
        bien.setFechaAdquisicion(bienDetails.getFechaAdquisicion());
        bien.setExpiracion(bienDetails.getExpiracion());
        bien.setValor(bienDetails.getValor());
        bien.setCentro(bienDetails.getCentro());

        if (bienDetails.getInstructor() != null && bienDetails.getInstructor().getId() != null) {
            Instructor instructor = instructorRepository.findById(bienDetails.getInstructor().getId())
                    .orElseThrow(() -> new RuntimeException("Instructor no encontrado"));
            bien.setInstructor(instructor);
        }

        return bienRepository.save(bien);
    }

    public void eliminar(Long id) {
        if (!bienRepository.existsById(id)) {
            throw new RuntimeException("Bien no encontrado con ID: " + id);
        }
        bienRepository.deleteById(id);
    }

    public List<Bien> getBienesByInstructor(Long instructorId) {
        return bienRepository.findByInstructor_Id(instructorId);
    }

    public Optional<Bien> getBienByPlaca(String placa) {
        return bienRepository.findByPlaca(placa);
    }

    public Optional<Bien> getBienByConsecutivo(String consecutivo) {
        return bienRepository.findByConsecutivo(consecutivo);
    }
}
