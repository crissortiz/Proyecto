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
@Table(name = "OrdenServicio")
public class OrdenServicio {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)      
    private Integer idOrden; 

    @Temporal(TemporalType.DATE)
    private Date fecha;           
    private String estadoOrden;   
    private String tipoOrden;     
    private String descripcion;   
    private String registroMedico; 
    private String idServicio;    
    private String idAfiliado;

    

    public OrdenServicio(Date fecha, String estadoOrden, String tipoOrden, String descripcion, String registroMedico, String idServicio, String idAfiliado) {
        this.fecha = fecha;
        this.estadoOrden = estadoOrden;
        this.tipoOrden = tipoOrden;
        this.descripcion = descripcion;
        this.registroMedico = registroMedico;
        this.idServicio = idServicio;
        this.idAfiliado = idAfiliado;
    }

    public OrdenServicio() 
    {;}

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