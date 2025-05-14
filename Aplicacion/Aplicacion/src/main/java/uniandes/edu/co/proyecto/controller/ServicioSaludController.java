package uniandes.edu.co.proyecto.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import uniandes.edu.co.proyecto.modelo.ServicioSalud;
import uniandes.edu.co.proyecto.repositorio.ServicioSaludRepository;

@Controller
public class ServicioSaludController {

    @Autowired
    private ServicioSaludRepository servicioSaludRepository;

    @GetMapping("/serviciosSalud")
    public String listarServiciosSalud(Model model) {
        model.addAttribute("serviciosSalud", servicioSaludRepository.findAllServiciosSalud());
        return "serviciosSalud"; // Vista para listar
    }

    @GetMapping("/serviciosSalud/new")
    public String nuevoServicioSalud(Model model) {
        model.addAttribute("servicioSalud", new ServicioSalud());
        return "servicioSaludNew"; // Vista para crear
    }

    @PostMapping("/serviciosSalud/new/save")
    public ResponseEntity<String> guardarNuevoServicioSalud(@RequestBody ServicioSalud servicioSalud) {
        servicioSaludRepository.createServicioSalud(
            servicioSalud.getIdServicio(),
            servicioSalud.getNombre(),
            servicioSalud.getDescripcion(),
            servicioSalud.getTipoServicio(),
            servicioSalud.isRequiereOrden() ? '1' : '0'
        );
        return ResponseEntity.ok("redirect:/serviciosSalud");
    }

    @GetMapping("/serviciosSalud/{id}/edit")
    public String editarServicioSalud(@PathVariable("id") Integer id, Model model) {
        ServicioSalud servicio = servicioSaludRepository.findServicioSaludById(id);
        if (servicio != null) {
            model.addAttribute("servicioSalud", servicio);
            return "servicioSaludEdit"; // Vista para editar
        } else {
            return "redirect:/serviciosSalud";
        }
    }

    @PostMapping("/serviciosSalud/{id}/edit/save")
    public String guardarEdicionServicioSalud(
        @PathVariable("id") Integer idServicio,
        @RequestParam("nombre") String nombre,
        @RequestParam("descripcion") String descripcion,
        @RequestParam("tipoServicio") String tipoServicio,
        @RequestParam(value = "requiereOrden", defaultValue = "false") boolean requiereOrden) {

        Character requiereOrdenChar = requiereOrden ? '1' : '0';
        servicioSaludRepository.updateServicioSalud(idServicio, nombre, descripcion, tipoServicio, requiereOrdenChar);
        return "redirect:/serviciosSalud";
    }

    @GetMapping("/serviciosSalud/{id}/delete")
    public String eliminarServicioSalud(@PathVariable("id") Integer id) {
        servicioSaludRepository.deleteServicioSalud(id);
        return "redirect:/serviciosSalud";
    }
}
