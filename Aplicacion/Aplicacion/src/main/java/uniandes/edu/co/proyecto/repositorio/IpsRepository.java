package uniandes.edu.co.proyecto.repositorio;

import java.util.Collection;

import uniandes.edu.co.proyecto.modelo.Ips;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import org.springframework.transaction.annotation.Transactional;

public interface IpsRepository extends JpaRepository<Ips, String> {
    
    @Query(value = "SELECT * FROM Ips", nativeQuery = true)
    Collection<Ips> findAllIps();

    @Query(value = "SELECT * FROM Ips WHERE nit = :nit", nativeQuery = true)
    Ips findIpsByNit(@Param("nit") String nit);

    @Modifying
    @Transactional
    @Query(value = "INSERT INTO Ips (nit, nombre, direccion, telefono) VALUES (:nit, :nombre, :direccion, :telefono)", nativeQuery = true)
    void CreateIps(@Param("nit") String nit, @Param("nombre") String nombre, @Param("direccion") String direccion, @Param("telefono") String telefono);
    
    @Modifying
    @Transactional
    @Query(value = "UPDATE Ips SET nit = :nit, nombre = :nombre, direccion = :direccion, telefono = :telefono)", nativeQuery = true)
    void UpdateIPS(@Param("nit") String nit, @Param("nombre") String nombre, @Param("direccion") String direccion, @Param("telefono") String telefono);

    @Modifying
    @Transactional
    @Query(value = "DELETE FROM Ips WHERE nit = :nit", nativeQuery = true)
    void DeleteIps(@Param("nit") String nit);
}
