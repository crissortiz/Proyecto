package uniandes.edu.co.proyecto.modelo;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "Atiende")
public class Atiende {

    @EmbeddedId
    private AtiendePK pk;
    public Atiende(Medico registroMedico, ServicioSalud idServicio) {
        this.pk = new AtiendePK(registroMedico, idServicio);
    }

    public Atiende() 
    {;}

    public AtiendePK getPk() {
        return pk;
    }

    public void setPk(AtiendePK pk) {
        this.pk = pk;
    }
    
}
    