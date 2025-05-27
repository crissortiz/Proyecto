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

    private String ordenServicioDescripcion;
    private int afiliadoNumDocumento;
    private int medicoRegistro;

    private int servicioId;       // 🆕 Para RFC1
    private String ipsNombre;     // 🆕 Para RFC1

    public Cita() {}

    public Cita(Date fecha, String estadoCita, String ordenServicioDescripcion,
                int afiliadoNumDocumento, int medicoRegistro,
                int servicioId, String ipsNombre) {
        this.fecha = fecha;
        this.estadoCita = estadoCita;
        this.ordenServicioDescripcion = ordenServicioDescripcion;
        this.afiliadoNumDocumento = afiliadoNumDocumento;
        this.medicoRegistro = medicoRegistro;
        this.servicioId = servicioId;
        this.ipsNombre = ipsNombre;
    }

    // Getters y setters

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

    public int getServicioId() {
        return servicioId;
    }

    public void setServicioId(int servicioId) {
        this.servicioId = servicioId;
    }

    public String getIpsNombre() {
        return ipsNombre;
    }

    public void setIpsNombre(String ipsNombre) {
        this.ipsNombre = ipsNombre;
    }
}

