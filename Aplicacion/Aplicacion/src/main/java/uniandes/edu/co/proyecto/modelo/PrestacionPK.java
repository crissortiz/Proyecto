package uniandes.edu.co.proyecto.modelo;

import java.io.Serializable;

import jakarta.persistence.Embeddable;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Embeddable
public class PrestacionPK implements Serializable{

    @ManyToOne
    @JoinColumn(name = "IPS_nit", referencedColumnName = "nit")
    private Ips nit;

    @ManyToOne
    @JoinColumn(name = "ServicioSalud_idServicio", referencedColumnName = "idServicio")
    private ServicioSalud idServicio;

    public PrestacionPK(Ips nit, ServicioSalud idServicio) {
        this.nit = nit;
        this.idServicio = idServicio;
    }

    public PrestacionPK() 
    {;}

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
