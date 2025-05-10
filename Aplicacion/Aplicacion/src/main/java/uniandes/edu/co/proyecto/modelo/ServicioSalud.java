package uniandes.edu.co.proyecto.modelo;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "ServicioSalud")
public class ServicioSalud {

    @Id
    private String idServicio;  // VARCHAR2(20)
    private String nombre;       // VARCHAR2(100)
    private String tipoServicio; // VARCHAR2(100)
    private boolean requiereOrden; // Boolean (1) - Map to boolean
    private String descripcion;  // VARCHAR2(300)

    public ServicioSalud() {
    }

    public ServicioSalud(String idServicio, String nombre, String tipoServicio, boolean requiereOrden, String descripcion) {
        this.idServicio = idServicio;
        this.nombre = nombre;
        this.tipoServicio = tipoServicio;
        this.requiereOrden = requiereOrden;
        this.descripcion = descripcion;
    }

    public String getIdServicio() {
        return idServicio;
    }

    public void setIdServicio(String idServicio) {
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