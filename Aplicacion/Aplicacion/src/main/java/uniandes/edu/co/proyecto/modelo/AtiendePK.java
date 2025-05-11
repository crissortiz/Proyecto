package uniandes.edu.co.proyecto.modelo;

import java.io.Serializable;

import jakarta.persistence.Embeddable;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Embeddable
public class AtiendePK implements Serializable{

    @ManyToOne
    @JoinColumn(name = "Medico_registroMedico1", referencedColumnName = "registroMedico")
    private Medico registroMedico;

    @ManyToOne
    @JoinColumn(name = "ServicioSalud_idServicio", referencedColumnName = "idServicio")
    private ServicioSalud idServicio;

    public AtiendePK(Medico registroMedico, ServicioSalud idServicio) {
        this.registroMedico = registroMedico;
        this.idServicio = idServicio;
    }

    public void setRegistroMedico(Medico registroMedico) {
        this.registroMedico = registroMedico;
    }
    public void setIdServicio(ServicioSalud idServicio) {
        this.idServicio = idServicio;
    }
    public Medico getRegistroMedico() {
        return registroMedico;
    }
    public ServicioSalud getIdServicio() {
        return idServicio;
    }
    
}
