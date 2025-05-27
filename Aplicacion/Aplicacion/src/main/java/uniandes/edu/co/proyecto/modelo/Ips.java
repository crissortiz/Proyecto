package uniandes.edu.co.proyecto.modelo;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.util.List;

@Document(collection = "ips")
public class Ips {

    @Id
    private String nit;

    private String nombre;
    private String direccion;
    private String telefono;

    private List<ServicioPrestado> serviciosPrestados;
    private List<MedicoIps> medicos;

    // Clases embebidas
    public static class ServicioPrestado {
        private int idServicio;
        private String nombre;
        private String tipo;
        private boolean requiereOrden;

        public ServicioPrestado() {}

        public ServicioPrestado(int idServicio, String nombre, String tipo, boolean requiereOrden) {
            this.idServicio = idServicio;
            this.nombre = nombre;
            this.tipo = tipo;
            this.requiereOrden = requiereOrden;
        }

        // Getters y setters
        public int getIdServicio() {
            return idServicio;
        }

        public void setIdServicio(int idServicio) {
            this.idServicio = idServicio;
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

        public boolean isRequiereOrden() {
            return requiereOrden;
        }

        public void setRequiereOrden(boolean requiereOrden) {
            this.requiereOrden = requiereOrden;
        }
    }

    public static class MedicoIps {
        private int registroMedico;
        private String nombre;
        private String especialidad;

        public MedicoIps() {}

        public MedicoIps(int registroMedico, String nombre, String especialidad) {
            this.registroMedico = registroMedico;
            this.nombre = nombre;
            this.especialidad = especialidad;
        }

        public int getRegistroMedico() {
            return registroMedico;
        }

        public void setRegistroMedico(int registroMedico) {
            this.registroMedico = registroMedico;
        }

        public String getNombre() {
            return nombre;
        }

        public void setNombre(String nombre) {
            this.nombre = nombre;
        }

        public String getEspecialidad() {
            return especialidad;
        }

        public void setEspecialidad(String especialidad) {
            this.especialidad = especialidad;
        }
    }

    // Constructores
    public Ips() {}

    public Ips(String nit, String nombre, String direccion, String telefono) {
        this.nit = nit;
        this.nombre = nombre;
        this.direccion = direccion;
        this.telefono = telefono;
    }

    // Getters y setters
    public String getNit() {
        return nit;
    }

    public void setNit(String nit) {
        this.nit = nit;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public List<ServicioPrestado> getServiciosPrestados() {
        return serviciosPrestados;
    }

    public void setServiciosPrestados(List<ServicioPrestado> serviciosPrestados) {
        this.serviciosPrestados = serviciosPrestados;
    }

    public List<MedicoIps> getMedicos() {
        return medicos;
    }

    public void setMedicos(List<MedicoIps> medicos) {
        this.medicos = medicos;
    }

    @Override
    public String toString() {
        return String.format("IPS [%s - %s]", nit, nombre);
    }
}
