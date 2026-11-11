package com.actividad3.crud_spring.models;

public class AlumnoForm {
    private String nombre;
    private String matricula;
    private Long escuelaId;

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public String getMatricula() { return matricula; }
    public void setMatricula(String matricula) { this.matricula = matricula; }
    public Long getEscuelaId() { return escuelaId; }
    public void setEscuelaId(Long escuelaId) { this.escuelaId = escuelaId; }
}