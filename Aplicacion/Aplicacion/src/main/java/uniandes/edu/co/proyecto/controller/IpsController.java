package uniandes.edu.co.proyecto.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import uniandes.edu.co.proyecto.modelo.Ips;
import uniandes.edu.co.proyecto.modelo.Ips.ServicioPrestado;
import uniandes.edu.co.proyecto.repositorio.IpsRepository;

import java.util.List;

@RestController
@RequestMapping("/ips")
public class IpsController {

    @Autowired
    private IpsRepository ipsRepository;

    // Crear nueva IPS
    @PostMapping("/new/save")
    public ResponseEntity<String> crearIps(@RequestBody Ips ips) {
        try {
            ipsRepository.save(ips);
            return new ResponseEntity<>("IPS creada exitosamente", HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>("Error al crear la IPS: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Obtener todas las IPS
    @GetMapping("")
    public ResponseEntity<List<Ips>> obtenerTodasLasIps() {
        try {
            List<Ips> lista = ipsRepository.findAll();
            return ResponseEntity.ok(lista);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    // Obtener IPS por nombre exacto
    @GetMapping("/nombre/{nombre}")
    public ResponseEntity<List<Ips>> obtenerIpsPorNombre(@PathVariable("nombre") String nombre) {
        try {
            List<Ips> resultado = ipsRepository.buscarPorNombre(nombre);
            if (resultado.isEmpty()) {
                return ResponseEntity.notFound().build();
            }
            return ResponseEntity.ok(resultado);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    // Buscar IPS por nombre parcial de servicio ofrecido (regex)
    @GetMapping("/servicio/nombre/{nombreParcial}")
    public ResponseEntity<List<Ips>> buscarIpsPorNombreDeServicio(@PathVariable("nombreParcial") String nombre) {
        try {
            List<Ips> resultado = ipsRepository.buscarIpsPorNombreServicio(nombre);
            return ResponseEntity.ok(resultado);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    // Buscar IPS por ID de servicio específico
    @GetMapping("/servicio/id/{idServicio}")
    public ResponseEntity<List<Ips>> buscarIpsPorIdServicio(@PathVariable("idServicio") int idServicio) {
        try {
            List<Ips> resultado = ipsRepository.buscarIpsPorIdServicio(idServicio);
            return ResponseEntity.ok(resultado);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    //Asignar un servicio a una IPS
    @PostMapping("/servicios/asignar")
    public ResponseEntity<String> asignarServicioAIPS(@RequestParam String nit, @RequestBody ServicioPrestado servicio) {
    try {
        ipsRepository.agregarServicioAIPS(nit, servicio);
        return ResponseEntity.ok("Servicio asignado correctamente a la IPS.");
    } catch (Exception e) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error: " + e.getMessage());
    }
}
}
