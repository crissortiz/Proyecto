package uniandes.edu.co.proyecto.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.ui.Model;

import java.util.Date;
import java.util.List;

import uniandes.edu.co.proyecto.dto.CitaDisponibleDTO;
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

    @GetMapping("/citas/{idServicio}/disponibles")
   public ResponseEntity<List<CitaDisponibleDTO>> getDisponibilidad(@RequestParam Integer servicioId) {
        List<CitaDisponibleDTO> disponibilidad = citaRepository.findDisponibilidadPorServicio(servicioId);
        return new ResponseEntity<>(disponibilidad, HttpStatus.OK);
    }


    @GetMapping("/citas/test/{valor}")
public ResponseEntity<String> testPathVariable(@PathVariable String valor) {
    return new ResponseEntity<>("El valor es: " + valor, HttpStatus.OK);
}

}
