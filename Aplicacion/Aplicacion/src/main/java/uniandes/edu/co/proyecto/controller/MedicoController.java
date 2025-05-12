package uniandes.edu.co.proyecto.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.ui.Model;

import uniandes.edu.co.proyecto.modelo.Medico;
import uniandes.edu.co.proyecto.repositorio.MedicoRepository;

@RestController
public class MedicoController {

    @Autowired
    private MedicoRepository medicoRepository;

    @GetMapping("/medicos")
    public String medicos(Model model) {
        model.addAttribute("medicos", medicoRepository.findAllMedicos());
        return model.toString();
    }

    @GetMapping("/medicos/new")
    public String medicoNuevo(Model model) {
        model.addAttribute("medico", new Medico());
        return "medico new";
    }

    @PostMapping("/medicos/new/save")
    public String guardarMedico(@ModelAttribute Medico medico) {
        medicoRepository.createMedico(
            medico.getRegistroMedico(),
            medico.getNombre(),
            medico.getTipoDocumento(),
            medico.getNumDocumento(),
            medico.getEspecialidad()
        );
        return "redirect:/medicos";
    }

    @GetMapping("/medicos/{id}/edit")
    public String editarMedico(@PathVariable("id") Integer id, Model model) {
        Medico medico = medicoRepository.findMedicoByRegistroMedico(id);
        if (medico != null) {
            model.addAttribute("medico", medico);
            return "medico edit";
        } else {
            return "redirect:/medicos";
        }
    }

    @PostMapping("/medicos/{id}/edit/save")
    public String guardarMedicoEditado(@PathVariable("id") Integer id, @ModelAttribute Medico medico) {
        medicoRepository.updateMedico(
            id,
            medico.getNombre(),
            medico.getTipoDocumento(),
            medico.getNumDocumento(),
            medico.getEspecialidad()
        );
        return "redirect:/medicos";
    }

    @GetMapping("/medicos/{id}/delete")
    public String eliminarMedico(@PathVariable("id") Integer id) {
        medicoRepository.deleteMedico(id);
        return "redirect:/medicos";
    }
}
