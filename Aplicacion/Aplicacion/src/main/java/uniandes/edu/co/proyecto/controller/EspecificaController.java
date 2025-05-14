package uniandes.edu.co.proyecto.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import uniandes.edu.co.proyecto.modelo.Especifica;
import uniandes.edu.co.proyecto.repositorio.EspecificaRepository;
import uniandes.edu.co.proyecto.repositorio.ServicioSaludRepository;
import uniandes.edu.co.proyecto.repositorio.OrdenServicioRepository;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.http.ResponseEntity;


@RestController
public class EspecificaController {

    @Autowired
    private EspecificaRepository especificaRepo;
    @Autowired
    private ServicioSaludRepository servicioRepo;
    @Autowired
    private OrdenServicioRepository ordenRepo;

    /** 1. Listar todas las asignaciones Servicio↔Orden */
    @GetMapping("/especifica")
    public String especifica(Model model){
        model.addAttribute("especifica", especificaRepo.findAllEspecifica());
        return model.toString(); // especifica.html
    }         //
    

    @GetMapping("/new")
    public String formNew(Model model) {
        model.addAttribute("especifica", new Especifica());
        model.addAttribute("servicios", servicioRepo.findAllServiciosSalud());
        model.addAttribute("ordenes",   ordenRepo.findAllOrdenesServicio());
        return "especifica_new";      // especifica_new.html
    }

    /** 3. Guardar la nueva asignación */
    @PostMapping("/especifica/new/save")
    public ResponseEntity<String> guardarEspecifica(@RequestBody Especifica especifica) {
        especificaRepo.createEspecifica(
            especifica.getPk().getIdServicio().getIdServicio(),
            especifica.getPk().getIdOrden().getIdOrden()
        );
        return ResponseEntity.ok("redirect:/especifica");
    }

    /** 4. Eliminar una asignación */
    @GetMapping("/{idServicio}/{idOrden}/delete")
    public String delete(@PathVariable("idServicio") Integer idServicio,
                         @PathVariable("idOrden")    Integer idOrden) {
        especificaRepo.deleteEspecifica(idOrden, idServicio);
        return "redirect:/especifica";
    }
}
