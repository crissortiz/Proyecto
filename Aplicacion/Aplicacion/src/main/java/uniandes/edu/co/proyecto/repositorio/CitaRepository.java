package uniandes.edu.co.proyecto.repositorio;

import uniandes.edu.co.proyecto.modelo.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import java.util.Collection;
import java.util.Date;

public interface CitaRepository extends JpaRepository<Cita, Integer> {
    @Query(value = "SELECT * FROM Cita", nativeQuery = true)
    Collection<Cita> findAllCitas();

    @Query(value = "SELECT * FROM Cita WHERE idCita = :idCita", nativeQuery = true)
    Cita findCitaById(@Param("idCita") Integer idCita);
    
    @Query(value = "SELECT * FROM Cita WHERE Medico_registroMedico = :registroMedico", nativeQuery = true)
    Collection<Cita> findCitasByMedico(@Param("registroMedico") Integer registroMedico);

    @Modifying
    @Transactional
    @Query(value = "INSERT INTO Cita (idCita, fecha, estadoCita, OrdenServicio_idOrden, Medico_registroMedico, Afiliado_idAfiliado) VALUES (:idCita, :fecha, :estadoCita, :OrdenServicio_idOrden, :Medico_registroMedico, :Afiliado_idAfiliado)", nativeQuery = true)
    void createCita(@Param("idCita") Integer idCita, @Param("fecha") Date fecha, @Param("estadoCita") String estadoCita, @Param("OrdenServicio_idOrden") Integer ordenServicioIdOrden, @Param("Medico_registroMedico") Integer medicoRegistroMedico, @Param("Afiliado_idAfiliado") Integer afiliadoIdAfiliado);

    @Modifying
    @Transactional
    @Query(value = "UPDATE Cita SET fecha = :fecha, estadoCita = :estadoCita, OrdenServicio_idOrden = :OrdenServicio_idOrden, Medico_registroMedico = :Medico_registroMedico, Afiliado_idAfiliado = :Afiliado_idAfiliado WHERE idCita = :idCita", nativeQuery = true)
    void updateCita(@Param("idCita") Integer idCita, @Param("fecha") Date fecha, @Param("estadoCita") String estadoCita, @Param("OrdenServicio_idOrden") Integer ordenServicioIdOrden, @Param("Medico_registroMedico") Integer medicoRegistroMedico, @Param("Afiliado_idAfiliado") Integer afiliadoIdAfiliado);

    @Modifying
    @Transactional
    @Query(value = "DELETE FROM Cita WHERE idCita = :idCita", nativeQuery = true)
    void deleteCita(@Param("idCita") Integer idCita);
}
