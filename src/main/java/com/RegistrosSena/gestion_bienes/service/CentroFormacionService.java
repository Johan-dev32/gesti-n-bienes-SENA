package com.RegistrosSena.gestion_bienes.service;

import com.RegistrosSena.gestion_bienes.model.CentroFormacion;
import com.RegistrosSena.gestion_bienes.model.Regional;
import com.RegistrosSena.gestion_bienes.repository.CentroFormacionRepository;
import com.RegistrosSena.gestion_bienes.repository.RegionalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class CentroFormacionService {

    @Autowired
    private CentroFormacionRepository centroRepository;

    @Autowired
    private RegionalRepository regionalRepository;

    public List<CentroFormacion> getAllCentros() {
        return centroRepository.findAll();
    }

    public CentroFormacion getCentroById(Long id) {
        return centroRepository.findById(id).orElse(null);
    }

    public CentroFormacion guardar(CentroFormacion centro) {

        if (centro.getRegional() != null && centro.getRegional().getId() != null) {
            Regional regional = regionalRepository
                    .findById(centro.getRegional().getId())
                    .orElse(null);

            centro.setRegional(regional);
        }

        return centroRepository.save(centro);
    }

    public void eliminar(Long id) {
        centroRepository.deleteById(id);
    }
}