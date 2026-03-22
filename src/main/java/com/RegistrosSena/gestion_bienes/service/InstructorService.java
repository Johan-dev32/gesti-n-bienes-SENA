package com.RegistrosSena.gestion_bienes.service;

import com.RegistrosSena.gestion_bienes.model.Instructor;
import com.RegistrosSena.gestion_bienes.repository.InstructorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class InstructorService {

    @Autowired
    private InstructorRepository instructorRepository;

    public List<Instructor> getAllInstructores() {
        return instructorRepository.findAll();
    }

    public Optional<Instructor> getInstructorById(Long id) {
        return instructorRepository.findById(id);
    }

    public Instructor createInstructor(Instructor instructor) {
        return instructorRepository.save(instructor);
    }

    public Instructor updateInstructor(Long id, Instructor instructorDetails) {
        Instructor instructor = instructorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Instructor no encontrado con ID: " + id));

        instructor.setNombres(instructorDetails.getNombres());
        instructor.setApellidos(instructorDetails.getApellidos());
        instructor.setCorreo(instructorDetails.getCorreo());
        instructor.setCelular(instructorDetails.getCelular());

        return instructorRepository.save(instructor);
    }

    public void deleteInstructor(Long id) {
        if (!instructorRepository.existsById(id)) {
            throw new RuntimeException("Instructor no encontrado con ID: " + id);
        }
        instructorRepository.deleteById(id);
    }

    public Instructor findByCorreo(String correo) {
        return instructorRepository.findByCorreo(correo)
                .orElseThrow(() -> new RuntimeException("Instructor no encontrado con correo: " + correo));
    }
}
