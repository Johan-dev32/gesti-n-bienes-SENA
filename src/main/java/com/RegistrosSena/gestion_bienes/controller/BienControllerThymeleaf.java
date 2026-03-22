package com.RegistrosSena.gestion_bienes.controller;

import com.RegistrosSena.gestion_bienes.model.Bien;
import com.RegistrosSena.gestion_bienes.model.CentroFormacion;
import com.RegistrosSena.gestion_bienes.model.Instructor;
import com.RegistrosSena.gestion_bienes.service.BienService;
import com.RegistrosSena.gestion_bienes.service.InstructorService;
import com.RegistrosSena.gestion_bienes.service.CentroFormacionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import java.util.List;

@Controller
@RequestMapping("/bien")
public class BienControllerThymeleaf {

    @Autowired
    private BienService bienService;

    @Autowired
    private InstructorService instructorService;

    @Autowired
    private CentroFormacionService centroService;

    @GetMapping("/lista")
    public String listarBienes(Model model) {
        List<Bien> bienes = bienService.getAllBienes();
        model.addAttribute("bienes", bienes);
        return "bien/lista";
    }

    @GetMapping("/formulario")
    public String formularioBien(Model model) {
        Bien bien = new Bien();
        List<Instructor> instructores = instructorService.getAllInstructores();
        List<CentroFormacion> centros = centroService.getAllCentros();
        model.addAttribute("bien", bien);
        model.addAttribute("instructores", instructores);
        model.addAttribute("centros", centros);

        return "bien/formulario";
    }

    @GetMapping("/formulario/{id}")
    public String formularioBienEdit(@PathVariable Long id, Model model) {
        Bien bien = bienService.getBienById(id)
                .orElseThrow(() -> new RuntimeException("Bien no encontrado con ID: " + id));
        List<Instructor> instructores = instructorService.getAllInstructores();
        model.addAttribute("bien", bien);
        model.addAttribute("instructores", instructores);
        return "bien/formulario";
    }

    @GetMapping("/detalle/{id}")
    public String detalleBien(@PathVariable Long id, Model model) {
        Bien bien = bienService.getBienById(id)
                .orElseThrow(() -> new RuntimeException("Bien no encontrado con ID: " + id));
        model.addAttribute("bien", bien);
        return "bien/detalle";
    }

    @PostMapping("/guardar")
    public String guardarBien(@ModelAttribute Bien bien, RedirectAttributes redirectAttributes) {
        try {
            if (bien.getId() == null) {
                bienService.guardar(bien);
                redirectAttributes.addFlashAttribute("mensaje", "Bien creado exitosamente");
            } else {
                bienService.updateBien(bien.getId(), bien);
                redirectAttributes.addFlashAttribute("mensaje", "Bien actualizado exitosamente");
            }
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Error al guardar bien: " + e.getMessage());
        }
        return "redirect:/bien/lista";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminarBien(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        try {
            bienService.eliminar(id);
            redirectAttributes.addFlashAttribute("mensaje", "Bien eliminado exitosamente");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Error al eliminar bien: " + e.getMessage());
        }
        return "redirect:/bien/lista";
    }
}
