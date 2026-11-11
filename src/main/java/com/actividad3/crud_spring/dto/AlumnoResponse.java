package com.actividad3.crud_spring.dto;

public record AlumnoResponse(Long id, String nombre, String matricula,
                             Long escuelaId, String escuelaNombre) {
}