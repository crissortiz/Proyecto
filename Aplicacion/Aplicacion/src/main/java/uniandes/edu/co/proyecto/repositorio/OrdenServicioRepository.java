package uniandes.edu.co.proyecto.repositorio;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import uniandes.edu.co.proyecto.modelo.OrdenServicio;

import java.util.List;

public interface OrdenServicioRepository extends MongoRepository<OrdenServicio, String> {

    // Buscar órdenes por número de documento del afiliado
    @Query("{ 'afiliadoNumDocumento' : ?0 }")
    List<OrdenServicio> buscarPorAfiliado(int numDocumento);

    // Buscar órdenes por estado
    @Query("{ 'estado' : ?0 }")
    List<OrdenServicio> buscarPorEstado(String estado);

    // Buscar órdenes por tipo de servicio (Servicio, Terapia)
    @Query("{ 'tipo' : ?0 }")
    List<OrdenServicio> buscarPorTipo(String tipo);
}
