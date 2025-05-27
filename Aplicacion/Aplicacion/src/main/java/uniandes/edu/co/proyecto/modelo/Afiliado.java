package uniandes.edu.co.proyecto.modelo;

import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.annotation.Id;
import java.util.Date;
import java.util.List;

@Document(collection = "afiliados")
public class Afiliado {

    @Id
    private String id;

    private String tipoDocumento;
    private Integer numDocumento;
    private String nombre;
    private Date fechaNacimiento;
    private String direccion;
    private String telefono;
    private String tipoAfiliado;

    private List<Beneficiario> beneficiarios;

    // Clase embebida
    public static class Beneficiario {
        private String tipoDocumento;
        private int numDocumento;
        private String nombre;
        private Date fechaNacimiento;
        private String parentesco;

        public Beneficiario() {}

        public String getTipoDocumento() {
            return tipoDocumento;
        }

        public void setTipoDocumento(String tipoDocumento) {
            this.tipoDocumento = tipoDocumento;
        }

        public int getNumDocumento() {
            return numDocumento;
        }

        public void setNumDocumento(int numDocumento) {
            this.numDocumento = numDocumento;
        }

        public String getNombre() {
            return nombre;
        }

        public void setNombre(String nombre) {
            this.nombre = nombre;
        }

        public Date getFechaNacimiento() {
            return fechaNacimiento;
        }

        public void setFechaNacimiento(Date fechaNacimiento) {
            this.fechaNacimiento = fechaNacimiento;
        }

        public String getParentesco() {
            return parentesco;
        }

        public void setParentesco(String parentesco) {
            this.parentesco = parentesco;
        }
    }

    public Afiliado() {}

    public Afiliado(String tipoDocumento, Integer numDocumento, String nombre, Date fechaNacimiento,
                    String direccion, String telefono, String tipoAfiliado, List<Beneficiario> beneficiarios) {
        this.tipoDocumento = tipoDocumento;
        this.numDocumento = numDocumento;
        this.nombre = nombre;
        this.fechaNacimiento = fechaNacimiento;
        this.direccion = direccion;
        this.telefono = telefono;
        this.tipoAfiliado = tipoAfiliado;
        this.beneficiarios = beneficiarios;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTipoDocumento() {
        return tipoDocumento;
    }

    public void setTipoDocumento(String tipoDocumento) {
        this.tipoDocumento = tipoDocumento;
    }

    public Integer getNumDocumento() {
        return numDocumento;
    }

    public void setNumDocumento(Integer numDocumento) {
        this.numDocumento = numDocumento;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
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

    public String getTipoAfiliado() {
        return tipoAfiliado;
    }

    public void setTipoAfiliado(String tipoAfiliado) {
        this.tipoAfiliado = tipoAfiliado;
    }

    public List<Beneficiario> getBeneficiarios() {
        return beneficiarios;
    }

    public void setBeneficiarios(List<Beneficiario> beneficiarios) {
        this.beneficiarios = beneficiarios;
    }

    @Override
    public String toString() {
        return String.format("Afiliado [%s - %s %s]", tipoDocumento, numDocumento, nombre);
    }
}
