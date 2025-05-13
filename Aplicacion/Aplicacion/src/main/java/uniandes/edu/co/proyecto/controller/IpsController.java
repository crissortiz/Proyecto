package uniandes.edu.co.proyecto.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import org.springframework.stereotype.Controller;


import org.springframework.ui.Model;

import uniandes.edu.co.proyecto.modelo.Ips;
import uniandes.edu.co.proyecto.repositorio.IpsRepository;

@Controller
public class IpsController {

    @Autowired
    private IpsRepository ipsRepository;

    @GetMapping("/ipss")
    public String ipss(Model model){
        model.addAttribute("ips", ipsRepository.findAllIps());
        return "ipss"; 
    }

    @GetMapping("/ipss/new")
    public String ipsNew(Model model){
        model.addAttribute("ips", new Ips());
        return "ips new";
    }

    @PostMapping("/ips/new/save")
    public String ipsGuardar(@ModelAttribute Ips ips) {
        ipsRepository.CreateIps(ips.getNit(), ips.getNombre(), ips.getDireccion(), ips.getTelefono());
        return "redirect:/ipss";
    }

    @GetMapping("/ipss/{nit}/edit")
        public String ipsEditarFrom(@PathVariable("nit") String nit, Model model) {
            Ips ips = ipsRepository.findIpsByNit(nit);
            if (ips != null) {
                model.addAttribute("ips", ips);
                return "ips edit";
            } else {
                return "redirect:/ipss";
            }
        }
    
    
    @PostMapping("/ipss/{nit}/edit/save")
    public String ipsEditarGuardar(@PathVariable("nit") String nit, @ModelAttribute Ips ips) {
        ipsRepository.UpdateIPS(ips.getNit(), ips.getNombre(), ips.getDireccion(), ips.getTelefono());
        return "redirect:/ipss";
    }

    @GetMapping("/ipss/{nit}/delete")
    public String ipsEliminar(@PathVariable("nit") String nit) {
        ipsRepository.DeleteIps(nit);
        return "redirect:/ipss";
    }

}