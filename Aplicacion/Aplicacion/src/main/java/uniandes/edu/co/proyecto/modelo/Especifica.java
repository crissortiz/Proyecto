package uniandes.edu.co.proyecto.modelo;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "Especifica")
public class Especifica {

    @EmbeddedId
    private EspecificaPK pk;
    public Especifica(ServicioSalud idServicio, OrdenServicio idOrden) {
        this.pk = new EspecificaPK(idServicio, idOrden);
    }

    public Especifica() 
    {;}

    public EspecificaPK getPk() {
        return pk;
    }

    public void setPk(EspecificaPK pk) {
        this.pk = pk;
    }
    
}
