package uniandes.edu.co.proyecto.modelo;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "ServicioSalud")
public class ServicioSalud {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer idServicio;  

    private String nombre;       
    private String tipoServicio; 
    private boolean requiereOrden;
    private String descripcion; 

    

    public ServicioSalud(Integer idServicio, String tipoServicio, boolean requiereOrden, String descripcion) {
 
        this.idServicio = idServicio;
        this.tipoServicio = tipoServicio;
        this.requiereOrden = requiereOrden;
        this.descripcion = descripcion;
    }

    public ServicioSalud() 
    {;}

    public Integer getIdServicio() {
        return idServicio;
    }

    public void setIdServicio(Integer idServicio) {
        this.idServicio = idServicio;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTipoServicio() {
        return tipoServicio;
    }

    public void setTipoServicio(String tipoServicio) {
        this.tipoServicio = tipoServicio;
    }

    public boolean isRequiereOrden() {
        return requiereOrden;
    }

    public void setRequiereOrden(boolean requiereOrden) {
        this.requiereOrden = requiereOrden;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}