package uniandes.edu.co.proyecto.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import uniandes.edu.co.proyecto.modelo.Prestacion;
import uniandes.edu.co.proyecto.modelo.PrestacionPK;
import uniandes.edu.co.proyecto.repositorio.PrestacionRepository;

import java.util.Collection;

@Controller
public class PrestacionController {

    @Autowired
    private PrestacionRepository prestacionRepo;



    @GetMapping("/prestacion")
    public String prestacion(Model model) {
        Collection<Prestacion> prestaciones = prestacionRepo.findAllPrestacion();
        model.addAttribute("prestaciones", prestaciones);
        return "prestacion"; 
    }

    @GetMapping("/prestacion/new")
    public String prestacionNew(Model model) {
        model.addAttribute("prestacion", prestacionRepo.findAllPrestacion());
        return "prestacion_new"; 

    }

    @PostMapping("/prestacion/new/save")
    public ResponseEntity<String> prestacionGuardar(@RequestBody Prestacion prestacion) {
        try {
            PrestacionPK pk = prestacion.getPk();
            String nit = pk.getNit().getNit();
            Integer idServicio = pk.getIdServicio().getIdServicio();

            prestacionRepo.createPrestacion(
                    nit,
                    idServicio
            );

            return ResponseEntity.ok("redirect:/prestacion");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error al guardar la prestación: " + e.getMessage());
        }
    }
    
    
    /** 4. Eliminar una prestación */
    @GetMapping("/{nit}/{idServicio}/delete")
    public String delete(@PathVariable("nit")        String nit,
                         @PathVariable("idServicio") Integer idServicio) {

        prestacionRepo.deletePrestacion(nit, idServicio);
        return "redirect:/prestacion";
    }
}

