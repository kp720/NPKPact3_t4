package com.actividad3.crud_spring.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.actividad3.crud_spring.models.City;
import com.actividad3.crud_spring.repository.CityRepository;

@Controller
public class CityController {

    @Autowired
    private CityRepository repo;

    // LISTAR
    @GetMapping("/")
    public String listar(Model model) {
        model.addAttribute("cities", repo.findAll());
        return "index";
    }

    // FORM NUEVO
    @GetMapping("/nuevo")
    public String nuevo(Model model) {
        model.addAttribute("city", new City());
        return "form";
    }

    // GUARDAR
    @PostMapping("/guardar")
    public String guardar(@ModelAttribute City city) {
        repo.save(city);
        return "redirect:/";
    }

    // EDITAR
    @GetMapping("/editar/{id}")
    public String editar(@PathVariable Integer id, Model model) {
        City city = repo.findById(id).orElse(null);
        model.addAttribute("city", city);
        return "form";
    }

    // ELIMINAR
    @GetMapping("/eliminar/{id}")
    public String eliminar(@PathVariable Integer id) {
        repo.deleteById(id);
        return "redirect:/";
    }
}
