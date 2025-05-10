package uniandes.edu.co.proyecto.modelo;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.util.Date;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

@Entity
@Table(name = "Cita")
public class Cita {

    @Id
    private String idCita;        // VARCHAR
    @Temporal(TemporalType.DATE)
    private Date fecha;           // Date
    private String estadoCita;    // VARCHAR
    private String idOrden;       // VARCHAR (FK to OrdenServicio)
    private String idAfiliado;    // VARCHAR (FK to Afiliado)
    private String registroMedico; // VARCHAR (FK to Medico)

    public Cita() {
    }

    public Cita(String idCita, Date fecha, String estadoCita, String idOrden, String idAfiliado, String registroMedico) {
        this.idCita = idCita;
        this.fecha = fecha;
        this.estadoCita = estadoCita;
        this.idOrden = idOrden;
        this.idAfiliado = idAfiliado;
        this.registroMedico = registroMedico;
    }

    public String getIdCita() {
        return idCita;
    }

    public void setIdCita(String idCita) {
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