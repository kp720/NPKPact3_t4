package com.actividad3.crud_spring.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import com.actividad3.crud_spring.dto.AlumnoRequest;
import com.actividad3.crud_spring.dto.AlumnoResponse;
import com.actividad3.crud_spring.service.AlumnoService;

import java.util.List;

@RestController
@RequestMapping("/api/alumnos")
public class AlumnoController {
    private final AlumnoService service;

    public AlumnoController(AlumnoService service) {
        this.service = service;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public AlumnoResponse crear(@RequestBody AlumnoRequest req) {
        return service.crear(req);
    }

    @GetMapping
    public List<AlumnoResponse> listar() {
        return service.listar();
    }

    @GetMapping("/{id}")
    public AlumnoResponse buscar(@PathVariable Long id) {
        return service.buscar(id);
    }

    @PutMapping("/{id}")
    public AlumnoResponse actualizar(@PathVariable Long id, @RequestBody AlumnoRequest req) {
        return service.actualizar(id, req);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void eliminar(@PathVariable Long id) {
        service.eliminar(id);
    }
}