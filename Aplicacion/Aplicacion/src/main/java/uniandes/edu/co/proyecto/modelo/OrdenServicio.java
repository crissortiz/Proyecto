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
    private Integer registroMedico; 
    private Integer idAfiliado;

    

    public OrdenServicio(Integer idOrden, Date fecha, String estadoOrden, String tipoOrden, String descripcion, Integer registroMedico, Integer idAfiliado) {
        
        this.idOrden = idOrden;
        this.fecha = fecha;
        this.estadoOrden = estadoOrden;
        this.tipoOrden = tipoOrden;
        this.descripcion = descripcion;
        this.registroMedico = registroMedico;
        this.idAfiliado = idAfiliado;
    }

    public OrdenServicio() 
    {;}

    public Integer getIdOrden() {
        return idOrden;
    }

    public void setIdOrden(Integer idOrden) {
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

    public Integer getRegistroMedico() {
        return registroMedico;
    }

    public void setRegistroMedico(Integer registroMedico) {
        this.registroMedico = registroMedico;
    }

    public Integer getIdAfiliado() {
        return idAfiliado;
    }

    public void setIdAfiliado(Integer idAfiliado) {
        this.idAfiliado = idAfiliado;
    }
}