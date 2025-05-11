package uniandes.edu.co.proyecto.modelo;

import java.io.Serializable;

import jakarta.persistence.Embeddable;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Embeddable
public class TrabajoPK implements Serializable{

    @ManyToOne
    @JoinColumn(name = "IPS_nit", referencedColumnName = "id")
    private Ips nit;

    @ManyToOne
    @JoinColumn(name = "Medico_registroMedico1", referencedColumnName = "id")
    private Medico registroMedico;

    public TrabajoPK(Ips nit, Medico registroMedico) {
        this.nit = nit;
        this.registroMedico = registroMedico;
    }

    public void setNit(Ips nit) {
        this.nit = nit;
    }
    public void setRegistroMedico(Medico registroMedico) {
        this.registroMedico = registroMedico;
    }
    public Ips getNit() {
        return nit;
    }
    public Medico getRegistroMedico() {
        return registroMedico;
    }
}
