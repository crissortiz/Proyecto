package uniandes.edu.co.proyecto.repositorio;

import uniandes.edu.co.proyecto.modelo.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import java.util.Collection;

public interface AtiendeRepository extends JpaRepository<Atiende, AtiendePK> { // Usa la clase de clave primaria compuesta

    @Query(value = "SELECT * FROM Atiende", nativeQuery = true)
    Collection<Atiende> findAllAtiende();

    @Query(value = "SELECT * FROM Atiende WHERE Medico_registroMedico = :registroMedico AND ServicioSalud_idServicio = :idServicio", nativeQuery = true)
    Atiende findAtiendeById(@Param("registroMedico") Integer registroMedico, @Param("idServicio") Integer idServicio);

    @Modifying
    @Transactional
    @Query(value = "INSERT INTO Atiende (Medico_registroMedico, ServicioSalud_idServicio) VALUES (:registroMedico, :idServicio)", nativeQuery = true)
    void createAtiende(@Param("registroMedico") Integer registroMedico, @Param("idServicio") Integer idServicio);

    @Modifying
    @Transactional
    @Query(value = "DELETE FROM Atiende WHERE Medico_registroMedico = :registroMedico AND ServicioSalud_idServicio = :idServicio", nativeQuery = true)
    void deleteAtiende(@Param("registroMedico") Integer registroMedico, @Param("idServicio") Integer idServicio);
}