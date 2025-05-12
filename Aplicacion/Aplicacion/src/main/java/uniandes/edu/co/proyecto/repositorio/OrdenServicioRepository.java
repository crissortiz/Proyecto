package uniandes.edu.co.proyecto.repositorio;

import uniandes.edu.co.proyecto.modelo.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import java.util.Collection;
import java.util.Date;

public interface OrdenServicioRepository extends JpaRepository<OrdenServicio, Integer> {
    @Query(value = "SELECT * FROM OrdenServicio", nativeQuery = true)
    Collection<OrdenServicio> findAllOrdenesServicio();

    @Query(value = "SELECT * FROM OrdenServicio WHERE idOrden = :idOrden", nativeQuery = true)
    OrdenServicio findOrdenServicioById(@Param("idOrden") Integer idOrden);

    @Query(value = "SELECT * FROM OrdenServicio WHERE Afiliado_idAfiliado = :idAfiliado", nativeQuery = true)
    Collection<OrdenServicio> findOrdenesServicioByAfiliado(@Param("idAfiliado") Integer idAfiliado);

    @Modifying
    @Transactional
    @Query(value = "INSERT INTO OrdenServicio (idOrden, fecha, estadoOrden, tipoOrden, descripcion, Afiliado_idAfiliado, Medico_registroMedico) VALUES (:idOrden, :fecha, :estadoOrden, :tipoOrden, :descripcion, :Afiliado_idAfiliado, :Medico_registroMedico)", nativeQuery = true)
    void createOrdenServicio(@Param("idOrden") Integer idOrden, @Param("fecha") Date fecha, @Param("estadoOrden") String estadoOrden, @Param("tipoOrden") String tipoOrden, @Param("descripcion") String descripcion, @Param("Afiliado_idAfiliado") Integer afiliadoIdAfiliado, @Param("Medico_registroMedico") Integer medicoRegistroMedico);

    @Modifying
    @Transactional
    @Query(value = "UPDATE OrdenServicio SET fecha = :fecha, estadoOrden = :estadoOrden, tipoOrden = :tipoOrden, descripcion = :descripcion, Afiliado_idAfiliado = :Afiliado_idAfiliado, Medico_registroMedico = :Medico_registroMedico WHERE idOrden = :idOrden", nativeQuery = true)
    void updateOrdenServicio(@Param("idOrden") Integer idOrden, @Param("fecha") Date fecha, @Param("estadoOrden") String estadoOrden, @Param("tipoOrden") String tipoOrden, @Param("descripcion") String descripcion, @Param("Afiliado_idAfiliado") Integer afiliadoIdAfiliado, @Param("Medico_registroMedico") Integer medicoRegistroMedico);

    @Modifying
    @Transactional
    @Query(value = "DELETE FROM OrdenServicio WHERE idOrden = :idOrden", nativeQuery = true)
    void deleteOrdenServicio(@Param("idOrden") Integer idOrden);
}
