package com.actividad3.crud_spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.actividad3.crud_spring.models.Alumno;

public interface AlumnoRepository extends JpaRepository<Alumno, Long> {
}