package uniandes.edu.co.proyecto.modelo;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "Prestacion")
public class Prestacion {

    @EmbeddedId
    private PrestacionPK pk;
    public Prestacion(Ips nit, ServicioSalud idServicio) {
        this.pk = new PrestacionPK(nit, idServicio);
    }

    public Prestacion() 
    {;}

    public PrestacionPK getPk() {
        return pk;
    }

    public void setPk(PrestacionPK pk) {
        this.pk = pk;
    }
    
}
