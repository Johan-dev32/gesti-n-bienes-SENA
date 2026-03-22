package com.RegistrosSena.gestion_bienes.controller;

import com.RegistrosSena.gestion_bienes.model.CentroFormacion;
import com.RegistrosSena.gestion_bienes.service.CentroFormacionService;
import com.RegistrosSena.gestion_bienes.service.RegionalService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class CentroFormacionController {

    private final CentroFormacionService service;
    private final RegionalService regionalService;

    public CentroFormacionController(CentroFormacionService service, RegionalService regionalService) {
        this.service = service;
        this.regionalService = regionalService;
    }

    @GetMapping("/centro")
    public String mostrarFormulario(Model model) {
        model.addAttribute("centro", new CentroFormacion());
        model.addAttribute("regionales", regionalService.listar());
        model.addAttribute("centros", service.getAllCentros());
        return "centro";
    }

    @PostMapping("/guardarCentro")
    public String guardar(@ModelAttribute CentroFormacion centro) {
        service.guardar(centro);
        return "redirect:/centro";
    }

    @GetMapping("/lista-centros")
    public String listarCentros(Model model) {
        model.addAttribute("centros", service.getAllCentros());
        return "centro/lista";
    }

    @GetMapping("/editar-centro/{id}")
    public String editarCentro(@PathVariable Long id, Model model) {
        CentroFormacion centro = service.getCentroById(id);

        model.addAttribute("centro", centro);
        model.addAttribute("centros", service.getAllCentros());
        model.addAttribute("regionales", regionalService.listar());

        return "centro";
    }

    @GetMapping("/eliminar-centro/{id}")
    public String eliminar(@PathVariable Long id) {
        service.eliminar(id);
        return "redirect:/centro";
    }

}