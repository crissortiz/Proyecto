package uniandes.edu.co.proyecto.repositorio;

import uniandes.edu.co.proyecto.modelo.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import java.util.Collection;

public interface TrabajoRepository extends JpaRepository<Trabajo, TrabajoPK> { // Usa la clase de clave primaria compuesta
    @Query(value = "SELECT * FROM Trabajo", nativeQuery = true)
    Collection<Trabajo> findAllTrabajo();

    @Query(value = "SELECT * FROM Trabajo WHERE Medico_registroMedico = :registroMedico AND IPS_nit = :nit", nativeQuery = true)
    Trabajo findTrabajoById(@Param("registroMedico") Integer registroMedico, @Param("nit") String nit);

    @Modifying
    @Transactional
    @Query(value = "INSERT INTO Trabajo (Medico_registroMedico, IPS_nit) VALUES (:registroMedico, :nit)", nativeQuery = true)
    void createTrabajo(@Param("registroMedico") Integer registroMedico, @Param("nit") String nit);

    @Modifying
    @Transactional
    @Query(value = "DELETE FROM Trabajo WHERE Medico_registroMedico = :registroMedico AND IPS_nit = :nit", nativeQuery = true)
    void deleteTrabajo(@Param("registroMedico") Integer registroMedico, @Param("nit") String nit);
}
