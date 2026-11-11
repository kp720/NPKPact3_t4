package com.actividad3.crud_spring.service;

import org.springframework.stereotype.Service;
import com.actividad3.crud_spring.models.Escuela;
import com.actividad3.crud_spring.repository.EscuelaRepository;
import java.util.List;

@Service
public class EscuelaService {
    private final EscuelaRepository repo;

    public EscuelaService(EscuelaRepository repo) {
        this.repo = repo;
    }

    public List<Escuela> listar() {
        return repo.findAll();
    }

    public Escuela crear(Escuela e) {
        e.setId(null);
        return repo.save(e);
    }
}