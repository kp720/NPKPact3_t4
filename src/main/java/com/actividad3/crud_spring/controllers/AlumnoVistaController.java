package com.actividad3.crud_spring.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.actividad3.crud_spring.dto.AlumnoRequest;
import com.actividad3.crud_spring.dto.AlumnoResponse;
import com.actividad3.crud_spring.models.AlumnoForm;
import com.actividad3.crud_spring.service.AlumnoService;
import com.actividad3.crud_spring.service.EscuelaService;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


@Controller
@RequestMapping("/alumnos")
public class AlumnoVistaController {

    private final AlumnoService alumnos;
    private final EscuelaService escuelas;

    public AlumnoVistaController(AlumnoService alumnos, EscuelaService escuelas) {
        this.alumnos = alumnos;
        this.escuelas = escuelas;
    }

    @GetMapping
    public String lista(Model model) {
        model.addAttribute("alumnos", alumnos.listar());
        return "alumnos";
    }

    @GetMapping("/nuevo")
    public String nuevo(Model model) {
        model.addAttribute("alumno", new AlumnoForm());
        model.addAttribute("escuelas", escuelas.listar());
        model.addAttribute("titulo", "Nuevo alumno");
        model.addAttribute("accion", "/alumnos/nuevo");
        return "alumno-form";
    }

    @PostMapping("/nuevo")
    public String crear(@ModelAttribute("alumno") AlumnoForm f, RedirectAttributes ra) {
        alumnos.crear(new AlumnoRequest(f.getNombre(), f.getMatricula(), f.getEscuelaId()));
        ra.addFlashAttribute("mensaje", "Alumno creado correctamente");
        return "redirect:/alumnos";
    }

    @GetMapping("/{id}/editar")
    public String editar(@PathVariable Long id, Model model) {
        AlumnoResponse r = alumnos.buscar(id);
        AlumnoForm f = new AlumnoForm();
        f.setNombre(r.nombre());
        f.setMatricula(r.matricula());
        f.setEscuelaId(r.escuelaId());

        model.addAttribute("alumno", f);
        model.addAttribute("escuelas", escuelas.listar());
        model.addAttribute("titulo", "Modificar alumno");
        model.addAttribute("accion", "/alumnos/" + id + "/editar");
        return "alumno-form";
    }

    @PostMapping("/{id}/editar")
    public String actualizar(@PathVariable Long id, @ModelAttribute("alumno") AlumnoForm f,
                             RedirectAttributes ra) {
        alumnos.actualizar(id, new AlumnoRequest(f.getNombre(), f.getMatricula(), f.getEscuelaId()));
        ra.addFlashAttribute("mensaje", "Alumno actualizado correctamente");
        return "redirect:/alumnos";
    }

    @PostMapping("/{id}/eliminar")
    public String eliminar(@PathVariable Long id, RedirectAttributes ra) {
        alumnos.eliminar(id);
        ra.addFlashAttribute("mensaje", "Alumno eliminado correctamente");
        return "redirect:/alumnos";
    }
}