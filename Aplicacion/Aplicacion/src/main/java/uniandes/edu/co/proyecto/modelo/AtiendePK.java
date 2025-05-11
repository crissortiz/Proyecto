package uniandes.edu.co.proyecto.modelo;

import java.io.Serializable;

import jakarta.persistence.Embeddable;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Embeddable
public class AtiendePK implements Serializable{

    @ManyToOne
    @JoinColumn(name = "Medico_registroMedico1", referencedColumnName = "id")
    private Medico registrMedico;

    @ManyToOne
    @JoinColumn(name = "ServicioSalud_idServicio", referencedColumnName = "id")
    private ServicioSalud idServicio;

    
}
