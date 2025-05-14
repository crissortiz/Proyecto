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
    private String descripcion; 
    private String tipoServicio; 
    private boolean requiereOrden;

    

    public ServicioSalud(Integer idServicio, String nombre,  String descripcion, String tipoServicio, boolean requiereOrden) {
 
        this.idServicio = idServicio;
        this.nombre = nombre;
        this.descripcion = descripcion;
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

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
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

    @Override
    public String toString() {
        return String.format("%-15s | %-15s | %-20s | %-15s | %-30s",
                idServicio, nombre, descripcion, tipoServicio, requiereOrden);}


}