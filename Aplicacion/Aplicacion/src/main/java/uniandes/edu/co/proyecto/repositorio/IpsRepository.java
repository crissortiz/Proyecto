package uniandes.edu.co.proyecto.repositorio;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import uniandes.edu.co.proyecto.modelo.Ips;
import java.util.List;

public interface IpsRepository extends MongoRepository<Ips, String> {

    // Buscar IPS por nombre exacto
    @Query("{ 'nombre' : ?0 }")
    List<Ips> buscarPorNombre(String nombre);

    // Buscar IPS que ofrezcan un servicio específico
    @Query("{ 'serviciosPrestados.idServicio' : ?0 }")
    List<Ips> buscarIpsPorIdServicio(int idServicio);

    // Buscar IPS por nombre de servicio ofrecido
    @Query("{ 'serviciosPrestados.nombre' : { $regex: ?0, $options: 'i' } }")
    List<Ips> buscarIpsPorNombreServicio(String nombreParcial);
}
