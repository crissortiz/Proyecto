package uniandes.edu.co.proyecto.modelo;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Document(collection = "citas")
public class Cita {

    @Id
    private String id;

    private Date fecha;
    private String estadoCita;

    // Referencias no embebidas (solo campos clave)
    private String ordenServicioDescripcion;
    private int afiliadoNumDocumento;
    private int medicoRegistro;

    public Cita() {}

    public Cita(Date fecha, String estadoCita, String ordenServicioDescripcion,
                int afiliadoNumDocumento, int medicoRegistro) {
        this.fecha = fecha;
        this.estadoCita = estadoCita;
        this.ordenServicioDescripcion = ordenServicioDescripcion;
        this.afiliadoNumDocumento = afiliadoNumDocumento;
        this.medicoRegistro = medicoRegistro;
    }

    // Getters y Setters

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public String getOrdenServicioDescripcion() {
        return ordenServicioDescripcion;
    }

    public void setOrdenServicioDescripcion(String ordenServicioDescripcion) {
        this.ordenServicioDescripcion = ordenServicioDescripcion;
    }

    public int getAfiliadoNumDocumento() {
        return afiliadoNumDocumento;
    }

    public void setAfiliadoNumDocumento(int afiliadoNumDocumento) {
        this.afiliadoNumDocumento = afiliadoNumDocumento;
    }

    public int getMedicoRegistro() {
        return medicoRegistro;
    }

    public void setMedicoRegistro(int medicoRegistro) {
        this.medicoRegistro = medicoRegistro;
    }
}
