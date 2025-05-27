package uniandes.edu.co.proyecto.modelo;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Document(collection = "ordenesServicio")
public class OrdenServicio {

    @Id
    private String id;

    private Date fecha;
    private String estado;      // Completada, Vigente, Vencida
    private String tipo;        // Servicio o Terapia
    private String descripcion;

    private Medico medico;
    private Servicio servicio;

    private int afiliadoNumDocumento; // Referencia ligera al afiliado

    public OrdenServicio() {}

    public OrdenServicio(Date fecha, String estado, String tipo, String descripcion,
                         Medico medico, Servicio servicio, int afiliadoNumDocumento) {
        this.fecha = fecha;
        this.estado = estado;
        this.tipo = tipo;
        this.descripcion = descripcion;
        this.medico = medico;
        this.servicio = servicio;
        this.afiliadoNumDocumento = afiliadoNumDocumento;
    }

    // --- Subdocumentos embebidos ---
    public static class Medico {
        private int registro;
        private String nombre;

        public Medico() {}

        public Medico(int registro, String nombre) {
            this.registro = registro;
            this.nombre = nombre;
        }

        public int getRegistro() {
            return registro;
        }

        public void setRegistro(int registro) {
            this.registro = registro;
        }

        public String getNombre() {
            return nombre;
        }

        public void setNombre(String nombre) {
            this.nombre = nombre;
        }
    }

    public static class Servicio {
        private int id;
        private String nombre;
        private String tipo;

        public Servicio() {}

        public Servicio(int id, String nombre, String tipo) {
            this.id = id;
            this.nombre = nombre;
            this.tipo = tipo;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getNombre() {
            return nombre;
        }

        public void setNombre(String nombre) {
            this.nombre = nombre;
        }

        public String getTipo() {
            return tipo;
        }

        public void setTipo(String tipo) {
            this.tipo = tipo;
        }
    }

    // --- Getters y Setters ---

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

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Medico getMedico() {
        return medico;
    }

    public void setMedico(Medico medico) {
        this.medico = medico;
    }

    public Servicio getServicio() {
        return servicio;
    }

    public void setServicio(Servicio servicio) {
        this.servicio = servicio;
    }

    public int getAfiliadoNumDocumento() {
        return afiliadoNumDocumento;
    }

    public void setAfiliadoNumDocumento(int afiliadoNumDocumento) {
        this.afiliadoNumDocumento = afiliadoNumDocumento;
    }
}
