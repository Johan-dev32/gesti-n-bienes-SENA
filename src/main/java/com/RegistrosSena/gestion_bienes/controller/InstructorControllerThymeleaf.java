package com.RegistrosSena.gestion_bienes.controller;

import com.RegistrosSena.gestion_bienes.model.Instructor;
import com.RegistrosSena.gestion_bienes.service.InstructorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import java.util.List;

@Controller
@RequestMapping("/instructor")
public class InstructorControllerThymeleaf {

    @Autowired
    private InstructorService instructorService;

    @GetMapping("/lista")
    public String listarInstructores(Model model) {
        List<Instructor> instructores = instructorService.getAllInstructores();
        model.addAttribute("instructores", instructores);
        return "instructor/lista";
    }

    @GetMapping("/formulario")
    public String formularioInstructor(Model model) {
        model.addAttribute("instructor", new Instructor());
        return "instructor/formulario";
    }

    @GetMapping("/formulario/{id}")
    public String formularioInstructorEdit(@PathVariable Long id, Model model) {
        Instructor instructor = instructorService.getInstructorById(id)
                .orElseThrow(() -> new RuntimeException("Instructor no encontrado"));
        model.addAttribute("instructor", instructor);
        return "instructor/formulario";
    }

    @GetMapping("/detalle/{id}")
    public String detalleInstructor(@PathVariable Long id, Model model) {
        Instructor instructor = instructorService.getInstructorById(id)
                .orElseThrow(() -> new RuntimeException("Instructor no encontrado"));
        model.addAttribute("instructor", instructor);
        return "instructor/detalle";
    }

    @PostMapping("/guardar")
    public String guardarInstructor(@ModelAttribute Instructor instructor, RedirectAttributes redirectAttributes) {
        try {
            if (instructor.getId() == null) {
                instructorService.createInstructor(instructor);
                redirectAttributes.addFlashAttribute("mensaje", "Instructor creado exitosamente");
            } else {
                instructorService.updateInstructor(instructor.getId(), instructor);
                redirectAttributes.addFlashAttribute("mensaje", "Instructor actualizado exitosamente");
            }
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Error al guardar instructor: " + e.getMessage());
        }
        return "redirect:/instructor/lista";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminarInstructor(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        try {
            instructorService.deleteInstructor(id);
            redirectAttributes.addFlashAttribute("mensaje", "Instructor eliminado exitosamente");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Error al eliminar instructor: " + e.getMessage());
        }
        return "redirect:/instructor/lista";
    }
}
