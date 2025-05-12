package uniandes.edu.co.proyecto.repositorio;

import uniandes.edu.co.proyecto.modelo.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import java.util.Collection;

public interface MedicoRepository extends JpaRepository<Medico, Integer> {

    @Query(value = "SELECT * FROM Medico", nativeQuery = true)
    Collection<Medico> findAllMedicos();

    @Query(value = "SELECT * FROM Medico WHERE registroMedico = :registroMedico", nativeQuery = true)
    Medico findMedicoByRegistroMedico(@Param("registroMedico") Integer registroMedico);

    @Modifying
    @Transactional
    @Query(value = "INSERT INTO Medico (registroMedico, nombre, tipoDocumento, numDocumento, especialidad) VALUES (:registroMedico, :nombre, :tipoDocumento, :numDocumento, :especialidad)", nativeQuery = true)
    void createMedico(@Param("registroMedico") Integer registroMedico, @Param("nombre") String nombre, @Param("tipoDocumento") String tipoDocumento, @Param("numDocumento") Integer numDocumento, @Param("especialidad") String especialidad);

    @Modifying
    @Transactional
    @Query(value = "UPDATE Medico SET nombre = :nombre, tipoDocumento = :tipoDocumento, numDocumento = :numDocumento, especialidad = :especialidad WHERE registroMedico = :registroMedico", nativeQuery = true)
    void updateMedico(@Param("registroMedico") Integer registroMedico, @Param("nombre") String nombre, @Param("tipoDocumento") String tipoDocumento, @Param("numDocumento") Integer numDocumento, @Param("especialidad") String especialidad);

    @Modifying
    @Transactional
    @Query(value = "DELETE FROM Medico WHERE registroMedico = :registroMedico", nativeQuery = true)
    void deleteMedico(@Param("registroMedico") Integer registroMedico);
}
