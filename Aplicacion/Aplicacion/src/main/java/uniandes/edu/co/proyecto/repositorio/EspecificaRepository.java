package uniandes.edu.co.proyecto.repositorio;

import uniandes.edu.co.proyecto.modelo.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import java.util.Collection;

public interface EspecificaRepository extends JpaRepository<Especifica, EspecificaPK> {  // Usa la clase de clave primaria compuesta
    @Query(value = "SELECT * FROM Especifica", nativeQuery = true)
    Collection<Especifica> findAllEspecifica();

    @Query(value = "SELECT * FROM Especifica WHERE OrdenServicio_idOrden = :idOrden AND ServicioSalud_idServicio = :idServicio", nativeQuery = true)
    Especifica findEspecificaById(@Param("idOrden") Integer idOrden, @Param("idServicio") Integer idServicio);

     @Modifying
    @Transactional
    @Query(value = "INSERT INTO Especifica (ServicioSalud_idServicio, OrdenServicio_idOrden) VALUES (:idServicio, :idOrden)", nativeQuery = true)
    void createEspecifica(@Param("idServicio") Integer idServicio, @Param("idOrden") Integer idOrden);

    @Modifying
    @Transactional
    @Query(value = "DELETE FROM Especifica WHERE OrdenServicio_idOrden = :idOrden AND ServicioSalud_idServicio = :idServicio", nativeQuery = true)
    void deleteEspecifica(@Param("idOrden") Integer idOrden, @Param("idServicio") Integer idServicio);
}
