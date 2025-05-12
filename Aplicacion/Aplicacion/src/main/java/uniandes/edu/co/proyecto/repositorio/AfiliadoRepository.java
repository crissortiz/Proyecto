package uniandes.edu.co.proyecto.repositorio;

import uniandes.edu.co.proyecto.modelo.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import java.util.Collection;
import java.util.Date;

public interface AfiliadoRepository extends JpaRepository<Afiliado, Integer> {

    @Query(value = "SELECT * FROM Afiliado", nativeQuery = true)
    Collection<Afiliado> findAllAfiliados();

    @Query(value = "SELECT * FROM Afiliado WHERE idAfiliado = :idAfiliado", nativeQuery = true)
    Afiliado findAfiliadoById(@Param("idAfiliado") Integer idAfiliado);

    @Modifying
    @Transactional
    @Query(value = "INSERT INTO Afiliado (idAfiliado, tipoDocumento, numDocumento, nombre, fechaNacimiento, direccion, telefono, tipoAfiliado, parentesco, afiliadoDependienteId) VALUES (:idAfiliado, :tipoDocumento, :numDocumento, :nombre, :fechaNacimiento, :direccion, :telefono, :tipoAfiliado, :parentesco, :afiliadoDependienteId)", nativeQuery = true)
    void createAfiliado(@Param("idAfiliado") Integer idAfiliado, @Param("tipoDocumento") String tipoDocumento, @Param("numDocumento") Integer numDocumento, @Param("nombre") String nombre, @Param("fechaNacimiento") Date fechaNacimiento, @Param("direccion") String direccion, @Param("telefono") String telefono, @Param("tipoAfiliado") String tipoAfiliado, @Param("parentesco") String parentesco, @Param("afiliadoDependienteId") Integer afiliadoDependienteId);

    @Modifying
    @Transactional
    @Query(value = "UPDATE Afiliado SET tipoDocumento = :tipoDocumento, numDocumento = :numDocumento, nombre = :nombre, fechaNacimiento = :fechaNacimiento, direccion = :direccion, telefono = :telefono, tipoAfiliado = :tipoAfiliado, parentesco = :parentesco, afiliadoDependienteId = :afiliadoDependienteId WHERE idAfiliado = :idAfiliado", nativeQuery = true)
    void updateAfiliado(@Param("idAfiliado") Integer idAfiliado, @Param("tipoDocumento") String tipoDocumento, @Param("numDocumento") Integer numDocumento, @Param("nombre") String nombre, @Param("fechaNacimiento") Date fechaNacimiento, @Param("direccion") String direccion, @Param("telefono") String telefono, @Param("tipoAfiliado") String tipoAfiliado, @Param("parentesco") String parentesco, @Param("afiliadoDependienteId") Integer afiliadoDependienteId);

    @Modifying
    @Transactional
    @Query(value = "DELETE FROM Afiliado WHERE idAfiliado = :idAfiliado", nativeQuery = true)
    void deleteAfiliado(@Param("idAfiliado") Integer idAfiliado);
}