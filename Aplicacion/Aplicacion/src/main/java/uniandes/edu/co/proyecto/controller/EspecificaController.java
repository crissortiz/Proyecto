package uniandes.edu.co.proyecto.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import uniandes.edu.co.proyecto.modelo.Especifica;
import uniandes.edu.co.proyecto.repositorio.EspecificaRepository;
import uniandes.edu.co.proyecto.repositorio.ServicioSaludRepository;
import uniandes.edu.co.proyecto.repositorio.OrdenServicioRepository;

import java.util.Collection;

@Controller
@RequestMapping("/especifica")
public class EspecificaController {

    @Autowired
    private EspecificaRepository especificaRepo;
    @Autowired
    private ServicioSaludRepository servicioRepo;
    @Autowired
    private OrdenServicioRepository ordenRepo;

    /** 1. Listar todas las asignaciones Servicio↔Orden */
    @GetMapping
    public String list(Model model) {
        Collection<Especifica> lista = especificaRepo.findAllEspecifica();
        model.addAttribute("especificaList", lista);
        return "especifica";         // especifica.html
    }

    /** 2. Formulario para nueva asignación */
    @GetMapping("/new")
    public String formNew(Model model) {
        model.addAttribute("especifica", new Especifica());
        model.addAttribute("servicios", servicioRepo.findAllServiciosSalud());
        model.addAttribute("ordenes",   ordenRepo.findAllOrdenesServicio());
        return "especifica_new";      // especifica_new.html
    }

    /** 3. Guardar la nueva asignación */
    @PostMapping("/new/save")
    public String saveNew(@RequestParam("idServicio") Integer idServicio,
                          @RequestParam("idOrden")   Integer idOrden) {
        especificaRepo.createEspecifica(idServicio, idOrden);
        return "redirect:/especifica";
    }

    /** 4. Eliminar una asignación */
    @GetMapping("/{idServicio}/{idOrden}/delete")
    public String delete(@PathVariable("idServicio") Integer idServicio,
                         @PathVariable("idOrden")    Integer idOrden) {
        especificaRepo.deleteEspecifica(idOrden, idServicio);
        return "redirect:/especifica";
    }
}
