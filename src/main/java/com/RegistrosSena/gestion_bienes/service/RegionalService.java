package com.RegistrosSena.gestion_bienes.service;

import com.RegistrosSena.gestion_bienes.model.Regional;
import com.RegistrosSena.gestion_bienes.repository.RegionalRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RegionalService {

    private final RegionalRepository repo;

    public RegionalService(RegionalRepository repo) {
        this.repo = repo;
    }

    public Regional guardar(Regional r) {
        return repo.save(r);
    }

    public List<Regional> listar() {
        return repo.findAll();
    }

    public Optional<Regional> getRegionalById(Long id) {
        return repo.findById(id);
    }
}