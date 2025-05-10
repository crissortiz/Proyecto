package uniandes.edu.co.proyecto.modelo;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.util.Date;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

@Entity
@Table(name = "OrdenServicio")
public class OrdenServicio {

    @Id
    private String idOrden;       // VARCHAR
    @Temporal(TemporalType.DATE)
    private Date fecha;           // Date
    private String estadoOrden;   // VARCHAR
    private String tipoOrden;     // VARCHAR
    private String descripcion;   // VARCHAR
    private String registroMedico; // VARCHAR  (FK to Medico)
    private String idServicio;    // VARCHAR  (FK to ServicioSalud)
    private String idAfiliado;

    public OrdenServicio() {
    }

    public OrdenServicio(String idOrden, Date fecha, String estadoOrden, String tipoOrden, String descripcion, String registroMedico, String idServicio, String idAfiliado) {
        this.idOrden = idOrden;
        this.fecha = fecha;
        this.estadoOrden = estadoOrden;
        this.tipoOrden = tipoOrden;
        this.descripcion = descripcion;
        this.registroMedico = registroMedico;
        this.idServicio = idServicio;
        this.idAfiliado = idAfiliado;
    }

    public String getIdOrden() {
        return idOrden;
    }

    public void setIdOrden(String idOrden) {
        this.idOrden = idOrden;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getEstadoOrden() {
        return estadoOrden;
    }

    public void setEstadoOrden(String estadoOrden) {
        this.estadoOrden = estadoOrden;
    }

    public String getTipoOrden() {
        return tipoOrden;
    }

    public void setTipoOrden(String tipoOrden) {
        this.tipoOrden = tipoOrden;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getRegistroMedico() {
        return registroMedico;
    }

    public void setRegistroMedico(String registroMedico) {
        this.registroMedico = registroMedico;
    }

    public String getIdServicio() {
        return idServicio;
    }

    public void setIdServicio(String idServicio) {
        this.idServicio = idServicio;
    }

    public String getIdAfiliado() {
        return idAfiliado;
    }

    public void setIdAfiliado(String idAfiliado) {
        this.idAfiliado = idAfiliado;
    }
}