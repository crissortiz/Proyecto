package uniandes.edu.co.proyecto.modelo;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "Medico")
public class Medico {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
        @Column(name = "REGISTROMEDICO")
    private Integer registroMedico;

    @Column(name = "NOMBRE")
    private String nombre;

    @Column(name = "TIPODOCUMENTO")
    private String tipoDocumento;

    @Column(name = "NUMDOCUMENTO")
    private Integer numDocumento;

    @Column(name = "ESPECIALIDAD")
    private String especialidad;

    public Medico(Integer registroMedico, String nombre, String tipoDocumento, Integer numDocumento, String especialidad) {
        
        this.registroMedico = registroMedico;
        this.nombre = nombre;
        this.tipoDocumento = tipoDocumento;
        this.numDocumento = numDocumento;
        this.especialidad = especialidad;
    }

    public Medico() 
    {;}

    public Integer getRegistroMedico() {
        return registroMedico;
    }

    public void setRegistroMedico(Integer registroMedico) {
        this.registroMedico = registroMedico;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
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

    public String getEspecialidad() {
        return especialidad;
    }

    public void setEspecialidad(String especialidad) {
        this.especialidad = especialidad;
    }

    @Override
    public String toString() {
        return String.format("%-15s | %-15s | %-20s | %-15s | %-30s | %-15s | %-15s | %-15s",
                registroMedico, nombre, tipoDocumento, numDocumento, especialidad);}



}