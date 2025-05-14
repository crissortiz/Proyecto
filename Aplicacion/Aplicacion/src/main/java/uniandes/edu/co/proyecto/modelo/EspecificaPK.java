package uniandes.edu.co.proyecto.modelo;

import java.io.Serializable;

import jakarta.persistence.Embeddable;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Embeddable
public class EspecificaPK implements Serializable {
    
    @ManyToOne
    @JoinColumn(name = "ServicioSalud_idServicio", referencedColumnName = "idServicio")
    private ServicioSalud idServicio;

    @ManyToOne
    @JoinColumn(name = "OrdenServicio_idOrden", referencedColumnName = "idOrden")
    private OrdenServicio idOrden;

    public EspecificaPK(ServicioSalud idServicio, OrdenServicio idOrden) {
        this.idServicio = idServicio;
        this.idOrden = idOrden;
    }

    public EspecificaPK() 
    {;}

    public void setIdServicio(ServicioSalud idServicio) {
        this.idServicio = idServicio;
    }
    public void setIdOrden(OrdenServicio idOrden) {
        this.idOrden = idOrden;
    }
    public ServicioSalud getIdServicio() {
        return idServicio;
    }
    public OrdenServicio getIdOrden() {
        return idOrden;
    }
}
