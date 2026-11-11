package com.actividad3.crud_spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.actividad3.crud_spring.models.Escuela;

public interface EscuelaRepository extends JpaRepository<Escuela, Long> {
}