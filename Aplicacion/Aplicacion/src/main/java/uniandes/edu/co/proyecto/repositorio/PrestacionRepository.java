package uniandes.edu.co.proyecto.repositorio;

import uniandes.edu.co.proyecto.modelo.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import java.util.Collection;

public interface PrestacionRepository extends JpaRepository<Prestacion, PrestacionPK> { // Usa la clase de clave primaria compuesta

    @Query(value = "SELECT * FROM Prestacion", nativeQuery = true)
    Collection<Prestacion> findAllPrestacion();
    
    @Query(value = "SELECT * FROM Prestacion WHERE IPS_nit = :nit AND ServicioSalud_idServicio = :idServicio", nativeQuery = true)
    Prestacion findPrestacionById(@Param("nit") String nit, @Param("idServicio") Integer idServicio);

    @Modifying
    @Transactional
    @Query(value = "INSERT INTO Prestacion (IPS_nit, ServicioSalud_idServicio) VALUES (:nit, :idServicio)", nativeQuery = true)
    void createPrestacion(@Param("nit") String nit, @Param("idServicio") Integer idServicio);

    @Modifying
    @Transactional
    @Query(value = "DELETE FROM Prestacion WHERE IPS_nit = :nit AND ServicioSalud_idServicio = :idServicio", nativeQuery = true)
    void deletePrestacion(@Param("nit") String nit, @Param("idServicio") Integer idServicio);
}
