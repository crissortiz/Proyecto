package uniandes.edu.co.proyecto.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import uniandes.edu.co.proyecto.modelo.Cita;
import uniandes.edu.co.proyecto.repositorio.CitaRepository;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/citas")
public class CitaController {

    @Autowired
    private CitaRepository citaRepository;

    // Crear nueva cita
    @PostMapping("/new/save")
    public ResponseEntity<String> crearCita(@RequestBody Cita cita) {
        try {
            citaRepository.save(cita);
            return new ResponseEntity<>("Cita creada exitosamente", HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>("Error al crear la cita: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Obtener todas las citas
    @GetMapping("")
    public ResponseEntity<List<Cita>> obtenerTodasLasCitas() {
        try {
            List<Cita> lista = citaRepository.findAll();
            return ResponseEntity.ok(lista);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    // Obtener citas por estado
    @GetMapping("/estado/{estado}")
    public ResponseEntity<List<Cita>> buscarPorEstado(@PathVariable("estado") String estado) {
        try {
            List<Cita> resultado = citaRepository.buscarPorEstado(estado);
            return ResponseEntity.ok(resultado);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    // Obtener citas por número de documento del afiliado
    @GetMapping("/afiliado/{numDocumento}")
    public ResponseEntity<List<Cita>> buscarPorAfiliado(@PathVariable("numDocumento") int doc) {
        try {
            List<Cita> resultado = citaRepository.buscarCitasFuturasPorAfiliado(doc, new Date());
            return ResponseEntity.ok(resultado);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    // Obtener citas disponibles por médico
    @GetMapping("/medico/{registro}/disponibles")
    public ResponseEntity<List<Cita>> buscarDisponiblesPorMedico(@PathVariable("registro") int reg) {
        try {
            List<Cita> resultado = citaRepository.buscarDisponiblesPorMedico(reg);
            return ResponseEntity.ok(resultado);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
