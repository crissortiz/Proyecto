package uniandes.edu.co.proyecto.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import uniandes.edu.co.proyecto.modelo.Afiliado;
import uniandes.edu.co.proyecto.repositorio.AfiliadoRepository;

import java.util.List;

@RestController
@RequestMapping("/afiliados")
public class AfiliadoController {

    @Autowired
    private AfiliadoRepository afiliadoRepository;

    // Crear un nuevo afiliado
    @PostMapping("/new/save")
    public ResponseEntity<String> crearAfiliado(@RequestBody Afiliado afiliado) {
        try {
            afiliadoRepository.save(afiliado);
            return new ResponseEntity<>("Afiliado creado exitosamente", HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>("Error al crear el afiliado: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Obtener todos los afiliados
    @GetMapping("")
    public ResponseEntity<List<Afiliado>> obtenerTodosLosAfiliados() {
        try {
            List<Afiliado> afiliados = afiliadoRepository.findAll();
            return ResponseEntity.ok(afiliados);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    // Obtener afiliado por número de documento
    @GetMapping("/{numDocumento}")
    public ResponseEntity<List<Afiliado>> obtenerPorDocumento(@PathVariable("numDocumento") int doc) {
        try {
            List<Afiliado> afiliados = afiliadoRepository.buscarPorNumDocumento(doc);
            if (afiliados.isEmpty()) {
                return ResponseEntity.notFound().build();
            }
            return ResponseEntity.ok(afiliados);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    // Eliminar afiliado por número de documento
    @DeleteMapping("/{numDocumento}/delete")
    public ResponseEntity<String> eliminarAfiliado(@PathVariable("numDocumento") int doc) {
        try {
            afiliadoRepository.eliminarPorNumDocumento(doc);
            return ResponseEntity.ok("Afiliado eliminado exitosamente");
        } catch (Exception e) {
            return new ResponseEntity<>("Error al eliminar afiliado: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Actualizar datos de un afiliado
    @PostMapping("/{numDocumento}/edit/save")
    public ResponseEntity<String> actualizarAfiliado(
            @PathVariable("numDocumento") int doc,
            @RequestBody Afiliado afiliado) {
        try {
            afiliadoRepository.actualizarAfiliado(
                doc,
                afiliado.getNombre(),
                afiliado.getDireccion(),
                afiliado.getTelefono()
            );
            return ResponseEntity.ok("Afiliado actualizado exitosamente");
        } catch (Exception e) {
            return new ResponseEntity<>("Error al actualizar afiliado: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
