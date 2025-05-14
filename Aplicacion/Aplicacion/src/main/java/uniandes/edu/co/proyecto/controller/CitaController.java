package uniandes.edu.co.proyecto.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.ui.Model;

import java.util.Collection;
import java.util.Date;

import uniandes.edu.co.proyecto.modelo.Cita;
import uniandes.edu.co.proyecto.repositorio.AfiliadoRepository;
import uniandes.edu.co.proyecto.repositorio.CitaRepository;

@RestController
public class CitaController {

    @Autowired
    private CitaRepository citaRepository;

    @Autowired
    private AfiliadoRepository afiliadoRepository;

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
    public ResponseEntity<String> guardarCita(@RequestBody Cita cita) {
        citaRepository.createCita(
            cita.getIdCita(),
            cita.getFecha() != null ? cita.getFecha() : new Date() ,
            cita.getEstadoCita(),
            cita.getIdOrden(),
            cita.getRegistroMedico(),
            cita.getIdAfiliado()
        );
        return ResponseEntity.ok("redirect:/citas");
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
    public ResponseEntity<String> guardarCitaEditada(@PathVariable("id") Integer id, @RequestBody Cita cita) {
        citaRepository.updateCita(
            id,
            cita.getFecha(),
            cita.getEstadoCita(),
            Integer.valueOf(cita.getIdOrden()),
            Integer.valueOf(cita.getRegistroMedico()),
            Integer.valueOf(cita.getIdAfiliado())
        );
        return ResponseEntity.ok("redirect:/citas");
    }

    @GetMapping("/citas/{id}/delete")
    public String eliminarCita(@PathVariable("id") Integer id) {
        citaRepository.deleteCita(id);
        return "redirect:/citas";
    }

    /** RF7-1: mostrar slots disponibles para un servicio */
    @GetMapping("/disponibles/{idServicio}")
    public String disponibles(@PathVariable Integer idServicio, Model model) {
        Collection<Cita> slots = citaRepository.findAvailableByServicio(idServicio);
        model.addAttribute("slots", slots);
        model.addAttribute("idServicio", idServicio);
        return "citas_disponibles";   // plantilla Thymeleaf
    }

    /** RF7-2: formulario para agendar una cita concreta */
    @GetMapping("/{idCita}/agendar")
    public String formAgendar(@PathVariable Integer idCita, Model model) {
        Cita cita = citaRepository.findById(idCita).orElse(null);
        model.addAttribute("cita", cita);
        model.addAttribute("afiliados", afiliadoRepository.findAllAfiliados());
        // El usuario debe introducir también el idOrden al que pertenece (ya existe en cita.getOrdenServicioIdOrden)
        return "cita_agendar";        // cita_agendar.html
    }

    /** RF7-2: acción de reserva */
    @PostMapping("/{idCita}/agendar/save")
    public String agendarSave(
            @PathVariable Integer idCita,
            @RequestParam("afiliadoId") Integer afiliadoId,
            @RequestParam("ordenId")   Integer ordenId) {

        Cita cita = citaRepository.findById(idCita).orElseThrow();
        cita.setIdAfiliado(afiliadoId);
        cita.setIdOrden(ordenId);
        cita.setEstadoCita("Ocupada");
        citaRepository.save(cita);
        return "redirect:/citas";
    }

}
