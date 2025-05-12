package uniandes.edu.co.proyecto.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import uniandes.edu.co.proyecto.modelo.OrdenServicio;
import uniandes.edu.co.proyecto.repositorio.OrdenServicioRepository;

import java.util.Date;

@Controller
public class OrdenServicioController {

    @Autowired
    private OrdenServicioRepository ordenServicioRepository;

    @GetMapping("/ordenesServicio")
    public String ordenesServicio(Model model) {
        model.addAttribute("ordenesServicio", ordenServicioRepository.findAllOrdenesServicio());
        return "ordenesServicio"; // Nombre de la vista
    }

    @GetMapping("/ordenesServicio/new")
    public String nuevaOrdenServicio(Model model) {
        model.addAttribute("ordenServicio", new OrdenServicio());
        return "ordenServicioNew"; // Vista para crear orden
    }

    @PostMapping("/ordenesServicio/new/save")
    public String guardarNuevaOrdenServicio(
        @RequestParam("idOrden") Integer idOrden,
        @RequestParam("fecha") @DateTimeFormat(pattern = "yyyy-MM-dd") Date fecha,
        @RequestParam("estadoOrden") String estadoOrden,
        @RequestParam("tipoOrden") String tipoOrden,
        @RequestParam("descripcion") String descripcion,
        @RequestParam("idAfiliado") Integer idAfiliado,
        @RequestParam("registroMedico") Integer registroMedico) {

        ordenServicioRepository.createOrdenServicio(idOrden, fecha, estadoOrden, tipoOrden, descripcion, idAfiliado, registroMedico);
        return "redirect:/ordenesServicio";
    }

    @GetMapping("/ordenesServicio/{id}/edit")
    public String editarOrdenServicio(@PathVariable("id") Integer id, Model model) {
        OrdenServicio orden = ordenServicioRepository.findOrdenServicioById(id);
        if (orden != null) {
            model.addAttribute("ordenServicio", orden);
            return "ordenServicioEdit"; // Vista para editar
        } else {
            return "redirect:/ordenesServicio";
        }
    }

    @PostMapping("/ordenesServicio/{id}/edit/save")
    public String guardarEdicionOrdenServicio(
        @PathVariable("id") Integer idOrden,
        @RequestParam("fecha") @DateTimeFormat(pattern = "yyyy-MM-dd") Date fecha,
        @RequestParam("estadoOrden") String estadoOrden,
        @RequestParam("tipoOrden") String tipoOrden,
        @RequestParam("descripcion") String descripcion,
        @RequestParam("idAfiliado") Integer idAfiliado,
        @RequestParam("registroMedico") Integer registroMedico) {

        ordenServicioRepository.updateOrdenServicio(idOrden, fecha, estadoOrden, tipoOrden, descripcion, idAfiliado, registroMedico);
        return "redirect:/ordenesServicio";
    }

    @GetMapping("/ordenesServicio/{id}/delete")
    public String eliminarOrdenServicio(@PathVariable("id") Integer idOrden) {
        ordenServicioRepository.deleteOrdenServicio(idOrden);
        return "redirect:/ordenesServicio";
    }
}
