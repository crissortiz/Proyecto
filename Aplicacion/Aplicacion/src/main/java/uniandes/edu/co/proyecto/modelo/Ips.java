package uniandes.edu.co.proyecto.modelo;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "Ips")
public class Ips {

    @Id
    private String nit;

    private String nombre;
    private String direccion;
    private String telefono;

    public Ips(String nit, String nombre, String direccion, String telefono){
        this.nit = nit;
        this.nombre = nombre;
        this.direccion = direccion;
        this.telefono = telefono;
    }
    public Ips() 
    {;}

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

    @Override
    public String toString(){
        return String.format("%-15s | %-15s | %-20s | %-15s",
                nit, nombre, direccion, telefono);
    }
    
}
