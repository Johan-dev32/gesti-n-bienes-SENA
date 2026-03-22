package com.RegistrosSena.gestion_bienes.controller;

import com.RegistrosSena.gestion_bienes.model.Regional;
import com.RegistrosSena.gestion_bienes.service.RegionalService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class RegionalController {

    private final RegionalService service;

    public RegionalController(RegionalService service) {
        this.service = service;
    }

    @GetMapping("/regional")
    public String mostrarFormulario(Model model) {
        model.addAttribute("regional", new Regional());
        model.addAttribute("regionales", service.listar());
        return "regional";
    }

    @PostMapping("/guardarRegional")
    public String guardar(@ModelAttribute Regional regional) {
        service.guardar(regional);
        return "redirect:/regional";
    }
}