package uniandes.edu.co.proyecto.modelo;

import java.io.Serializable;

import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

public class PrestacionPK implements Serializable{

    @ManyToOne
    @JoinColumn(name = "IPS_nit", referencedColumnName = "id")
    private Ips nit;

    @ManyToOne
    @JoinColumn(name = "ServicioSalud_idServicio", referencedColumnName = "id")
    private ServicioSalud idServicio;

    public PrestacionPK(Ips nit, ServicioSalud idServicio) {
        this.nit = nit;
        this.idServicio = idServicio;
    }

    public void setNit(Ips nit) {
        this.nit = nit;
    }
    public void setIdServicio(ServicioSalud idServicio) {
        this.idServicio = idServicio;
    }
    public Ips getNit() {
        return nit;
    }
    public ServicioSalud getIdServicio() {
        return idServicio;
    }
    
}
