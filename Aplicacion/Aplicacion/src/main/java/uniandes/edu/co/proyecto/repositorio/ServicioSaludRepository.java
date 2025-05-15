package uniandes.edu.co.proyecto.repositorio;

import uniandes.edu.co.proyecto.modelo.*;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;


import java.util.Collection;

public interface ServicioSaludRepository extends JpaRepository<ServicioSalud, Integer> {
    @Query(value = "SELECT * FROM ServicioSalud", nativeQuery = true)
    Collection<ServicioSalud> findAllServiciosSalud();

    @Query(value = "SELECT * FROM ServicioSalud WHERE idServicio = :idServicio", nativeQuery = true)
    ServicioSalud findServicioSaludById(@Param("idServicio") Integer idServicio);

    @Modifying
    @Transactional
    @Query(value = "INSERT INTO ServicioSalud (idServicio, nombre, descripcion, tipoServicio, requiereOrden) VALUES (:idServicio, :nombre, :descripcion, :tipoServicio, :requiereOrden)", nativeQuery = true)
    void createServicioSalud(@Param("idServicio") Integer idServicio, @Param("nombre") String nombre, @Param("descripcion") String descripcion, @Param("tipoServicio") String tipoServicio, @Param("requiereOrden") Character requiereOrden);

    @Modifying
    @Transactional
    @Query(value = "UPDATE ServicioSalud SET nombre = :nombre, descripcion = :descripcion, tipoServicio = :tipoServicio, requiereOrden = :requiereOrden WHERE idServicio = :idServicio", nativeQuery = true)
    void updateServicioSalud(@Param("idServicio") Integer idServicio, @Param("nombre") String nombre, @Param("descripcion") String descripcion, @Param("tipoServicio") String tipoServicio, @Param("requiereOrden") Character requiereOrden);

    @Modifying
    @Transactional
    @Query(value = "DELETE FROM ServicioSalud WHERE idServicio = :idServicio", nativeQuery = true)
    void deleteServicioSalud(@Param("idServicio") Integer idServicio);
}