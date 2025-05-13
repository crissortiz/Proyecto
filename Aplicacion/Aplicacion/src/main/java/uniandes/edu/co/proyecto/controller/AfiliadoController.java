package uniandes.edu.co.proyecto.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import uniandes.edu.co.proyecto.modelo.Afiliado;
import uniandes.edu.co.proyecto.repositorio.AfiliadoRepository;

import java.util.Date;

@Controller
public class AfiliadoController {

    @Autowired
    private AfiliadoRepository afiliadoRepository;

    @GetMapping("/afiliados")
    public String afiliados(Model model){
        model.addAttribute("afiliados", afiliadoRepository.findAllAfiliados());
        return "afiliados"; // Debes tener un archivo afiliados.html en templates
    }

    @GetMapping("/afiliados/new")
    public String afiliadoNew(Model model){
        model.addAttribute("afiliado", new Afiliado());
        return "afiliado_new"; // afiliado_new.html
    }

    @PostMapping("/afiliados/new/save")
    public String afiliadoGuardar(@ModelAttribute Afiliado afiliado) {
        afiliadoRepository.createAfiliado(
            afiliado.getIdAfiliado(),
            afiliado.getTipoDocumento(),
            afiliado.getNumDocumento(),
            afiliado.getNombre(),
            afiliado.getFechaNacimiento() != null ? afiliado.getFechaNacimiento() : new Date(),
            afiliado.getDireccion(),
            afiliado.getTelefono(),
            afiliado.getTipoAfiliado(),
            afiliado.getParentesco(),
            afiliado.getAfiliadoDependienteId()
        );
        return "redirect:/afiliados";
    }

    @GetMapping("/afiliados/{id}/edit")
    public String afiliadoEditarForm(@PathVariable("id") Integer idAfiliado, Model model) {
        Afiliado afiliado = afiliadoRepository.findAfiliadoById(idAfiliado);
        if (afiliado != null) {
            model.addAttribute("afiliado", afiliado);
            return "afiliado_edit"; // afiliado_edit.html
        } else {
            return "redirect:/afiliados";
        }
    }

    @PostMapping("/afiliados/{id}/edit/save")
    public String afiliadoEditarGuardar(@PathVariable("id") Integer idAfiliado, @ModelAttribute Afiliado afiliado) {
        afiliadoRepository.updateAfiliado(
            idAfiliado,
            afiliado.getTipoDocumento(),
            afiliado.getNumDocumento(),
            afiliado.getNombre(),
            afiliado.getFechaNacimiento(),
            afiliado.getDireccion(),
            afiliado.getTelefono(),
            afiliado.getTipoAfiliado(),
            afiliado.getParentesco(),
            afiliado.getAfiliadoDependienteId()
        );
        return "redirect:/afiliados";
    }

    @GetMapping("/afiliados/{id}/delete")
    public String afiliadoEliminar(@PathVariable("id") Integer idAfiliado) {
        afiliadoRepository.deleteAfiliado(idAfiliado);
        return "redirect:/afiliados";
    }
}

