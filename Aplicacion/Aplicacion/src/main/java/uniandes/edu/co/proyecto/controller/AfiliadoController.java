package uniandes.edu.co.proyecto.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import org.springframework.ui.Model;

import uniandes.edu.co.proyecto.modelo.Afiliado;
import uniandes.edu.co.proyecto.repositorio.AfiliadoRepository;

import java.util.Date;

@RestController
public class AfiliadoController {

    @Autowired
    private AfiliadoRepository afiliadoRepository;

    @GetMapping("/afiliados")
    public String afiliados(Model model){
        model.addAttribute("afiliados", afiliadoRepository.findAllAfiliados());
        return model.toString(); // Aquí podrías devolver un HTML si cambias a @Controller
    }

    @GetMapping("/afiliados/new")
    public String afiliadoNew(Model model){
        model.addAttribute("afiliado", new Afiliado());
        return "afiliado new";
    }

    @PostMapping("/afiliados/new/save")
    public String afiliadoGuardar(@ModelAttribute Afiliado afiliado) {
        // Nota: Usa valores dummy para campos faltantes si no tienes UI aún
        afiliadoRepository.createAfiliado(
            afiliado.getIdAfiliado(),
            afiliado.getTipoDocumento(),
            afiliado.getNumDocumento(),
            afiliado.getNombre(),
            afiliado.getFechaNacimiento() != null ? afiliado.getFechaNacimiento() : new Date(),
            afiliado.getDireccion(),
            afiliado.getTelefono(),
            afiliado.getTipoAfiliado(),
            "SinParentesco", // si no se maneja aún
            null             // afiliadoDependienteId
        );
        return "redirect:/afiliados";
    }

    @GetMapping("/afiliados/{id}/edit")
    public String afiliadoEditarForm(@PathVariable("id") Integer idAfiliado, Model model) {
        Afiliado afiliado = afiliadoRepository.findAfiliadoById(idAfiliado);
        if (afiliado != null) {
            model.addAttribute("afiliado", afiliado);
            return "afiliado edit";
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
            "SinParentesco",
            null
        );
        return "redirect:/afiliados";
    }

    @GetMapping("/afiliados/{id}/delete")
    public String afiliadoEliminar(@PathVariable("id") Integer idAfiliado) {
        afiliadoRepository.deleteAfiliado(idAfiliado);
        return "redirect:/afiliados";
    }
}
