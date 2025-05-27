package uniandes.edu.co.proyecto.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import uniandes.edu.co.proyecto.modelo.Cita;
import uniandes.edu.co.proyecto.modelo.OrdenServicio;
import uniandes.edu.co.proyecto.repositorio.CitaRepository;
import uniandes.edu.co.proyecto.repositorio.CitaRepositoryCustom;
import uniandes.edu.co.proyecto.repositorio.OrdenServicioRepository;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.bson.Document; 

@RestController
@RequestMapping("/citas")
public class CitaController {

    @Autowired
    private CitaRepository citaRepository;

    @Autowired
    private OrdenServicioRepository ordenServicioRepository;

    @Autowired
    private CitaRepositoryCustom citaRepositoryCustom;

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

    @GetMapping("/mas-solicitados")
    public ResponseEntity<List<Document>> obtenerServiciosMasSolicitados(
        @RequestParam("inicio") long inicioMillis,
        @RequestParam("fin") long finMillis) {
    try {
        Date inicio = new Date(inicioMillis);
        Date fin = new Date(finMillis);
        List<Document> resultado = citaRepositoryCustom.obtenerServiciosMasSolicitados(inicio, fin);
        return ResponseEntity.ok(resultado);
        } catch (Exception e) {
        e.printStackTrace();
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @PostMapping("/agendar")
    public ResponseEntity<String> agendarCita(
        @RequestParam String servicio,
        @RequestParam int afiliado,
        @RequestParam String citaId) {
        try {
        // Verificar si la cita existe y está disponible
        Optional<Cita> citaOpt = citaRepository.findById(citaId);
        if (citaOpt.isEmpty() || !"Disponible".equalsIgnoreCase(citaOpt.get().getEstadoCita())) {
            return ResponseEntity.badRequest().body("La cita no está disponible.");
        }

        // Buscar orden vigente para ese afiliado y servicio
        List<OrdenServicio> ordenes = ordenServicioRepository.buscarPorAfiliado(afiliado);
        boolean tieneOrden = ordenes.stream().anyMatch(o ->
            "Vigente".equalsIgnoreCase(o.getEstado()) &&
            o.getServicio().getNombre().equalsIgnoreCase(servicio)
        );

        // Servicios que NO requieren orden
        boolean requiereOrden = !servicio.equalsIgnoreCase("Consulta General") &&
                                !servicio.equalsIgnoreCase("Consulta de Urgencias");

        if (requiereOrden && !tieneOrden) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN)
                    .body("El servicio requiere orden vigente, pero no se encontró ninguna.");
        }

        // Si todo está bien, marcar la cita como ocupada
        citaRepository.marcarCitaComoOcupada(citaId);
        return ResponseEntity.ok("Cita agendada exitosamente.");
        } catch (Exception e) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body("Error al agendar la cita: " + e.getMessage());
        }
    }

    @GetMapping("/disponibilidad")
    public ResponseEntity<List<Map<String, Object>>> consultarDisponibilidadServicio(
        @RequestParam("idServicio") int idServicio) {

    try {
        // Rango de fechas: hoy hasta dentro de 4 semanas
        Date ahora = new Date();
        Calendar cal = Calendar.getInstance();
        cal.setTime(ahora);
        cal.add(Calendar.DAY_OF_YEAR, 28);
        Date en4Semanas = cal.getTime();

        // Buscar citas disponibles en ese rango
        List<Cita> citas = citaRepository.buscarDisponiblesPorServicioYFecha(idServicio, ahora, en4Semanas);

        // Estructurar respuesta completa
        List<Map<String, Object>> respuesta = new ArrayList<>();
        for (Cita cita : citas) {
            Map<String, Object> info = new HashMap<>();
            info.put("fecha", cita.getFecha());
            info.put("estado", cita.getEstadoCita());
            info.put("servicio", cita.getOrdenServicioDescripcion());
            info.put("idServicio", cita.getServicioId());
            info.put("ips", cita.getIpsNombre());
            info.put("medicoRegistro", cita.getMedicoRegistro());
            respuesta.add(info);
        }

        return ResponseEntity.ok(respuesta);

    } catch (Exception e) {
        e.printStackTrace();
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }
}


}
