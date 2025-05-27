package uniandes.edu.co.proyecto.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import uniandes.edu.co.proyecto.modelo.OrdenServicio;
import uniandes.edu.co.proyecto.repositorio.OrdenServicioRepository;

import java.util.List;

@RestController
@RequestMapping("/ordenes")
public class OrdenServicioController {

    @Autowired
    private OrdenServicioRepository ordenServicioRepository;

    // Crear una nueva orden
    @PostMapping("/new/save")
    public ResponseEntity<String> crearOrden(@RequestBody OrdenServicio orden) {
        try {
            ordenServicioRepository.save(orden);
            return new ResponseEntity<>("Orden creada exitosamente", HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>("Error al crear la orden: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Obtener todas las órdenes
    @GetMapping("")
    public ResponseEntity<List<OrdenServicio>> obtenerTodas() {
        try {
            List<OrdenServicio> lista = ordenServicioRepository.findAll();
            return ResponseEntity.ok(lista);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    // Buscar por número de documento del afiliado
    @GetMapping("/afiliado/{numDocumento}")
    public ResponseEntity<List<OrdenServicio>> buscarPorAfiliado(@PathVariable("numDocumento") int doc) {
        try {
            List<OrdenServicio> lista = ordenServicioRepository.buscarPorAfiliado(doc);
            return ResponseEntity.ok(lista);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    // Buscar por estado (Vigente, Completada, Vencida)
    @GetMapping("/estado/{estado}")
    public ResponseEntity<List<OrdenServicio>> buscarPorEstado(@PathVariable("estado") String estado) {
        try {
            List<OrdenServicio> lista = ordenServicioRepository.buscarPorEstado(estado);
            return ResponseEntity.ok(lista);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    // Buscar por tipo (Servicio o Terapia)
    @GetMapping("/tipo/{tipo}")
    public ResponseEntity<List<OrdenServicio>> buscarPorTipo(@PathVariable("tipo") String tipo) {
        try {
            List<OrdenServicio> lista = ordenServicioRepository.buscarPorTipo(tipo);
            return ResponseEntity.ok(lista);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
