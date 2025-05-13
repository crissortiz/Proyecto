package uniandes.edu.co.proyecto.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import uniandes.edu.co.proyecto.modelo.Trabajo;
import uniandes.edu.co.proyecto.repositorio.TrabajoRepository;
import uniandes.edu.co.proyecto.repositorio.IpsRepository;
import uniandes.edu.co.proyecto.repositorio.MedicoRepository;

import java.util.Collection;

@Controller
@RequestMapping("/trabajo")
public class TrabajoController {

    @Autowired
    private TrabajoRepository trabajoRepo;
    @Autowired
    private IpsRepository ipsRepo;
    @Autowired
    private MedicoRepository medicoRepo;

    /** 1. Listar todas las relaciones IPS↔Médico */
    @GetMapping
    public String list(Model model) {
        Collection<Trabajo> lista = trabajoRepo.findAllTrabajo();
        model.addAttribute("trabajoList", lista);
        return "trabajo";         // trabajo.html
    }

    /** 2. Formulario para nueva relación */
    @GetMapping("/new")
    public String formNew(Model model) {
        model.addAttribute("trabajo", new Trabajo());
        model.addAttribute("ipses", ipsRepo.findAllIps());
        model.addAttribute("medicos", medicoRepo.findAllMedicos());
        return "trabajo_new";     // trabajo_new.html
    }

    /** 3. Guardar la nueva relación */
    @PostMapping("/new/save")
    public String saveNew(@RequestParam("nit")           String nit,
                          @RequestParam("registroMedico") Integer registroMedico) {

        trabajoRepo.createTrabajo(registroMedico, nit);
        return "redirect:/trabajo";
    }

    /** 4. Eliminar una relación */
    @GetMapping("/{registroMedico}/{nit}/delete")
    public String delete(@PathVariable("registroMedico") Integer registroMedico,
                         @PathVariable("nit")             String nit) {

        trabajoRepo.deleteTrabajo(registroMedico, nit);
        return "redirect:/trabajo";
    }
}
