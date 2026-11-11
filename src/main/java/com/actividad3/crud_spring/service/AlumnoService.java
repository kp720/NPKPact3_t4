package com.actividad3.crud_spring.service;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import com.actividad3.crud_spring.dto.AlumnoRequest;
import com.actividad3.crud_spring.dto.AlumnoResponse;
import com.actividad3.crud_spring.models.Alumno;
import com.actividad3.crud_spring.models.Escuela;
import com.actividad3.crud_spring.repository.AlumnoRepository;
import com.actividad3.crud_spring.repository.EscuelaRepository;

import java.util.List;

@Service
@Transactional
public class AlumnoService {
    private final AlumnoRepository alumnos;
    private final EscuelaRepository escuelas;

    public AlumnoService(AlumnoRepository alumnos, EscuelaRepository escuelas) {
        this.alumnos = alumnos;
        this.escuelas = escuelas;
    }

    @Transactional(readOnly = true)
    public List<AlumnoResponse> listar() {
        return alumnos.findAll().stream().map(this::aDto).toList();
    }

    @Transactional(readOnly = true)
    public AlumnoResponse buscar(Long id) {
        return aDto(obtener(id));
    }

    public AlumnoResponse crear(AlumnoRequest req) {
        Alumno a = new Alumno();
        aplicar(a, req);
        return aDto(alumnos.save(a));
    }

    public AlumnoResponse actualizar(Long id, AlumnoRequest req) {
        Alumno a = obtener(id);
        aplicar(a, req);
        return aDto(alumnos.save(a));
    }

    public void eliminar(Long id) {
        alumnos.delete(obtener(id));
    }

    private Alumno obtener(Long id) {
        return alumnos.findById(id).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "Alumno no encontrado"));
    }

    private void aplicar(Alumno a, AlumnoRequest req) {
        Escuela e = escuelas.findById(req.escuelaId()).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.BAD_REQUEST, "La escuela no existe"));
        a.setNombre(req.nombre());
        a.setMatricula(req.matricula());
        a.setEscuela(e);
    }

    private AlumnoResponse aDto(Alumno a) {
        return new AlumnoResponse(a.getId(), a.getNombre(), a.getMatricula(),
                a.getEscuela().getId(), a.getEscuela().getNombre());
    }
}