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
@Table(name = "Afiliado")
public class Afiliado {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer idAfiliado;   

    private String nombre;         
    @Temporal(TemporalType.DATE)
    private Date fechaNacimiento;  
    private String tipoAfiliado;  
    private String tipoDocumento;  
    private Integer numDocumento;   
    private String direccion;      
    private String telefono;       
    private String idAfiliado1;   


    

    public Afiliado(Integer idAfiliado, String nombre, Date fechaNacimiento, String tipoAfiliado, String tipoDocumento, Integer numDocumento, String direccion, String telefono, String idAfiliado1, Medico medico) {

        this.idAfiliado = idAfiliado;
        this.nombre = nombre;
        this.fechaNacimiento = fechaNacimiento;
        this.tipoAfiliado = tipoAfiliado;
        this.tipoDocumento = tipoDocumento;
        this.numDocumento = numDocumento;
        this.direccion = direccion;
        this.telefono = telefono;
        this.idAfiliado1 = idAfiliado1;
    }

    public Afiliado() 
    {;}

    public Integer getIdAfiliado() {
        return idAfiliado;
    }
    public void setIdAfiliado(Integer idAfiliado) {
        this.idAfiliado = idAfiliado;
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

    public String getTipoAfiliado() {
        return tipoAfiliado;
    }

    public void setTipoAfiliado(String tipoAfiliado) {
        this.tipoAfiliado = tipoAfiliado;
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

    public String getIdAfiliado1() {
        return idAfiliado1;
    }

    public void setIdAfiliado1(String idAfiliado1) {
        this.idAfiliado1 = idAfiliado1;
    }


}