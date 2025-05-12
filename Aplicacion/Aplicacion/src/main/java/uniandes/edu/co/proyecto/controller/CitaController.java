package uniandes.edu.co.proyecto.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.ui.Model;

import uniandes.edu.co.proyecto.modelo.Cita;
import uniandes.edu.co.proyecto.repositorio.CitaRepository;

@RestController
public class CitaController {

    @Autowired
    private CitaRepository citaRepository;

    @GetMapping("/citas")
    public String citas(Model model) {
        model.addAttribute("citas", citaRepository.findAllCitas());
        return model.toString();
    }

    @GetMapping("/citas/new")
    public String citaNueva(Model model) {
        model.addAttribute("cita", new Cita());
        return "cita new";
    }

    @PostMapping("/citas/new/save")
    public String guardarCita(@ModelAttribute Cita cita) {
        citaRepository.createCita(
            cita.getIdCita(),
            cita.getFecha(),
            cita.getEstadoCita(),
            Integer.valueOf(cita.getIdOrden()),
            Integer.valueOf(cita.getRegistroMedico()),
            Integer.valueOf(cita.getIdAfiliado())
        );
        return "redirect:/citas";
    }

    @GetMapping("/citas/{id}/edit")
    public String editarCita(@PathVariable("id") Integer id, Model model) {
        Cita cita = citaRepository.findCitaById(id);
        if (cita != null) {
            model.addAttribute("cita", cita);
            return "cita edit";
        } else {
            return "redirect:/citas";
        }
    }

    @PostMapping("/citas/{id}/edit/save")
    public String guardarCitaEditada(@PathVariable("id") Integer id, @ModelAttribute Cita cita) {
        citaRepository.updateCita(
            id,
            cita.getFecha(),
            cita.getEstadoCita(),
            Integer.valueOf(cita.getIdOrden()),
            Integer.valueOf(cita.getRegistroMedico()),
            Integer.valueOf(cita.getIdAfiliado())
        );
        return "redirect:/citas";
    }

    @GetMapping("/citas/{id}/delete")
    public String eliminarCita(@PathVariable("id") Integer id) {
        citaRepository.deleteCita(id);
        return "redirect:/citas";
    }
}
