package com.RegistrosSena.gestion_bienes.repository;

import com.RegistrosSena.gestion_bienes.model.Bien;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BienRepository extends JpaRepository<Bien, Long> {

    Optional<Bien> findByPlaca(String placa);

    Optional<Bien> findByConsecutivo(String consecutivo);

    List<Bien> findByInstructor_Id(Long instructorId);

}
