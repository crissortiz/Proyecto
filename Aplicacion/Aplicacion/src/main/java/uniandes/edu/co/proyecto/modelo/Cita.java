package uniandes.edu.co.proyecto.modelo;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.util.Date;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

@Entity
@Table(name = "Cita")
public class Cita {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer idCita;    
    
    @Temporal(TemporalType.DATE)
    private Date fecha;           
    private String estadoCita;    
    private String idOrden;       
    private String idAfiliado;    
    private String registroMedico; 

    public Cita(Integer idCita, Date fecha, String estadoCita, String idOrden, String idAfiliado, String registroMedico) {
        
        this.idCita = idCita;
        this.fecha = fecha;
        this.estadoCita = estadoCita;
        this.idOrden = idOrden;
        this.idAfiliado = idAfiliado;
        this.registroMedico = registroMedico;
    }

    public Cita() 
    {;}

    public Integer getIdCita() {
        return idCita;
    }
    public void setIdCita(Integer idCita) {
        this.idCita = idCita;
    }
    
    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getEstadoCita() {
        return estadoCita;
    }

    public void setEstadoCita(String estadoCita) {
        this.estadoCita = estadoCita;
    }

    public String getIdOrden() {
        return idOrden;
    }

    public void setIdOrden(String idOrden) {
        this.idOrden = idOrden;
    }

    public String getIdAfiliado() {
        return idAfiliado;
    }

    public void setIdAfiliado(String idAfiliado) {
        this.idAfiliado = idAfiliado;
    }

    public String getRegistroMedico() {
        return registroMedico;
    }

    public void setRegistroMedico(String registroMedico) {
        this.registroMedico = registroMedico;
    }
}