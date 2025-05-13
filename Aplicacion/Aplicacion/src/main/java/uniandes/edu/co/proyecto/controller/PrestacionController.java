package uniandes.edu.co.proyecto.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import uniandes.edu.co.proyecto.modelo.Prestacion;
import uniandes.edu.co.proyecto.repositorio.PrestacionRepository;
import uniandes.edu.co.proyecto.repositorio.IpsRepository;
import uniandes.edu.co.proyecto.repositorio.ServicioSaludRepository;
import uniandes.edu.co.proyecto.repositorio.MedicoRepository;

import java.util.Collection;

@Controller
@RequestMapping("/prestacion")
public class PrestacionController {

    @Autowired
    private PrestacionRepository prestacionRepo;
    @Autowired
    private IpsRepository ipsRepo;
    @Autowired
    private ServicioSaludRepository servicioRepo;
    @Autowired
    private MedicoRepository medicoRepo;

    /** 1. Listar todas las prestaciones */
    @GetMapping
    public String list(Model model) {
        Collection<Prestacion> lista = prestacionRepo.findAllPrestacion();
        model.addAttribute("prestacionList", lista);
        return "prestacion";        // prestacion.html
    }

    /** 2. Formulario para nueva prestación */
    @GetMapping("/new")
    public String formNew(Model model) {
        model.addAttribute("prestacion", new Prestacion());
        model.addAttribute("ipses", ipsRepo.findAllIps());
        model.addAttribute("servicios", servicioRepo.findAllServiciosSalud());
        model.addAttribute("medicos", medicoRepo.findAllMedicos());
        return "prestacion_new";    // prestacion_new.html
    }

    /** 3. Guardar la nueva prestación */
    @PostMapping("/new/save")
    public String saveNew(@RequestParam("nit")           String nit,
                          @RequestParam("idServicio")    Integer idServicio,
                          @RequestParam("registroMedico") Integer registroMedico) {

        prestacionRepo.createPrestacion(nit, idServicio, registroMedico);
        return "redirect:/prestacion";
    }

    /** 4. Eliminar una prestación */
    @GetMapping("/{nit}/{idServicio}/delete")
    public String delete(@PathVariable("nit")        String nit,
                         @PathVariable("idServicio") Integer idServicio) {

        prestacionRepo.deletePrestacion(nit, idServicio);
        return "redirect:/prestacion";
    }
}
