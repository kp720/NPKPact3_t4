package com.actividad3.crud_spring.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import com.actividad3.crud_spring.models.Escuela;
import com.actividad3.crud_spring.service.EscuelaService;

import java.util.List;

@RestController
@RequestMapping("/api/escuelas")
public class EscuelaController {
    private final EscuelaService service;

    public EscuelaController(EscuelaService service) {
        this.service = service;
    }

    @GetMapping
    public List<Escuela> listar() {
        return service.listar();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Escuela crear(@RequestBody Escuela escuela) {
        return service.crear(escuela);
    }
}
