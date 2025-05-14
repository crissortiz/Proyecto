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
    
    @Temporal(TemporalType.TIMESTAMP)
    private Date fecha;           
    private String estadoCita;    
    private Integer idOrden;       
    private Integer idAfiliado;    
    private Integer registroMedico; 

    public Cita(Integer idCita, Date fecha, String estadoCita, Integer idOrden, Integer idAfiliado, Integer registroMedico) {
        
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

    public Integer getIdOrden() {
        return idOrden;
    }

    public void setIdOrden(Integer idOrden) {
        this.idOrden = idOrden;
    }

    public Integer getIdAfiliado() {
        return idAfiliado;
    }

    public void setIdAfiliado(Integer idAfiliado) {
        this.idAfiliado = idAfiliado;
    }

    public Integer getRegistroMedico() {
        return registroMedico;
    }

    public void setRegistroMedico(Integer registroMedico) {
        this.registroMedico = registroMedico;
    }
}