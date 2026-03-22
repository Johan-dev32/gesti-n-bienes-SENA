package com.RegistrosSena.gestion_bienes.repository;

import com.RegistrosSena.gestion_bienes.model.Instructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface InstructorRepository extends JpaRepository<Instructor, Long> {

    Optional<Instructor> findByCorreo(String correo);

    Optional<Instructor> findByNombresAndApellidos(String nombres, String apellidos);
}
