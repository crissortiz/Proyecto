package uniandes.edu.co.proyecto.dto;

import java.util.Date;

public class RegistroPrestacionDTO {
    private String ipsNit;
    private Integer idServicio;
    private Integer idAfiliado;
    private Integer registroMedico;
    private Date fecha;
    private String hora; 

    public RegistroPrestacionDTO(String ipsNit, Integer idServicio, Integer idAfiliado, Integer registroMedico, Date fecha, String hora) {
        this.ipsNit = ipsNit;
        this.idServicio = idServicio;
        this.idAfiliado = idAfiliado;
        this.registroMedico = registroMedico;
        this.fecha = fecha;
        this.hora = hora;
    }

    public String getIpsNit() {
        return ipsNit;
    }
    public void setIpsNit(String ipsNit) {
        this.ipsNit = ipsNit;
    }
    public Integer getIdServicio() {
        return idServicio;
    }
    public void setIdServicio(Integer idServicio) {
        this.idServicio = idServicio;
    }
    public Integer getIdAfiliado() {
        return idAfiliado;
    }
    public void setIdAfiliado(Integer idAfiliado) {
        this.idAfiliado = idAfiliado;
    }
    public Integer getRegistroMedico() {
        return registroMedico;
    }
    public void setRegistroMedico(Integer registroMedico) {
        this.registroMedico = registroMedico;
    }
    public Date getFecha() {
        return fecha;
    }
    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }
    public String getHora() {
        return hora;
    }
    public void setHora(String hora) {
        this.hora = hora;
    }
    

    
}
