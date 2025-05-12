package uniandes.edu.co.proyecto.controller;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import uniandes.edu.co.proyecto.modelo.Ips;
import uniandes.edu.co.proyecto.repositorio.IpsRepository;

@RestController
public class IpsController {

    @Autowired
    private IpsRepository ipsRepository;

    @GetMapping("/ipss")
    public ResponseEntity<Collection<Ips>> ipss() {
        try{
            Collection<Ips> ipss = ipsRepository.findAllIps();
        return ResponseEntity.ok(ipss);
    }   catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    //Incompleto
    @GetMapping("/ips/{nit}")
    public ResponseEntity<Ips> ips(@PathVariable String nit) {
        try{
            Ips ips = ipsRepository.findIpsByNit(nit);
            if (ips != null) {
                return ResponseEntity.ok(ips);
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PostMapping("/ips/new/save")
    public ResponseEntity<String> ipsGuardar(@RequestBody Ips ips) {
        try {
            ipsRepository.CreateIps(ips.getNit(), ips.getNombre(), ips.getDireccion(), ips.getTelefono());
            return new ResponseEntity<>("IPS creada correctamente", HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>("Error al crear la ips", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    

}