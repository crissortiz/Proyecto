package uniandes.edu.co.proyecto.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import uniandes.edu.co.proyecto.modelo.Atiende;
import uniandes.edu.co.proyecto.modelo.AtiendePK;
import uniandes.edu.co.proyecto.modelo.Medico;
import uniandes.edu.co.proyecto.modelo.ServicioSalud;
import uniandes.edu.co.proyecto.repositorio.AtiendeRepository;
import uniandes.edu.co.proyecto.repositorio.MedicoRepository;
import uniandes.edu.co.proyecto.repositorio.ServicioSaludRepository;

import java.util.Collection;

@Controller
@RequestMapping("/atiende")
public class AtiendeController {

    @Autowired
    private AtiendeRepository atiendeRepo;
    @Autowired
    private MedicoRepository medicoRepo;
    @Autowired
    private ServicioSaludRepository servicioRepo;

    /** 1. Listar todas las asignaciones */
    @GetMapping
    public String list(Model model) {
        Collection<Atiende> lista = atiendeRepo.findAllAtiende();
        model.addAttribute("atiendeList", lista);
        return "atiende";          // atiende.html
    }

    /** 2. Formulario nueva asignación */
    @GetMapping("/new")
    public String formNew(Model model) {
        model.addAttribute("atiende", new Atiende());
        model.addAttribute("medicos", medicoRepo.findAllMedicos());
        model.addAttribute("servicios", servicioRepo.findAllServiciosSalud());
        return "atiende_new";      // atiende_new.html
    }

    /** 3. Guardar la nueva asignación */
    @PostMapping("/new/save")
    public String saveNew(@RequestParam("registroMedico") Integer regMed,
                          @RequestParam("idServicio")   Integer idSrv) {
        atiendeRepo.createAtiende(regMed, idSrv);
        return "redirect:/atiende";
    }

    /** 4. Eliminar una asignación */
    @GetMapping("/{regMed}/{idSrv}/delete")
    public String delete(@PathVariable("regMed") Integer regMed,
                         @PathVariable("idSrv")   Integer idSrv) {
        atiendeRepo.deleteAtiende(regMed, idSrv);
        return "redirect:/atiende";
    }
}
