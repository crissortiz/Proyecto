package uniandes.edu.co.proyecto.modelo;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "Trabajo")
public class Trabajo {

    @EmbeddedId
    private TrabajoPK pk;
    public Trabajo(Ips nit, Medico registroMedico) {
        this.pk = new TrabajoPK(nit, registroMedico);
    }

    public Trabajo() 
    {;}

    public TrabajoPK getPk() {
        return pk;
    }

    public void setPk(TrabajoPK pk) {
        this.pk = pk;
    }
    
}
