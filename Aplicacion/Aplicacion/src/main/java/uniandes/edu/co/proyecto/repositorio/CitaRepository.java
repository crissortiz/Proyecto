package uniandes.edu.co.proyecto.repositorio;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import uniandes.edu.co.proyecto.modelo.Cita;

import java.util.Date;
import java.util.List;

public interface CitaRepository extends MongoRepository<Cita, String> {

    // Buscar citas por estado (Disponible, Ocupada, Completa)
    @Query("{ 'estadoCita' : ?0 }")
    List<Cita> buscarPorEstado(String estadoCita);

    // Buscar todas las citas futuras para un afiliado
    @Query("{ 'afiliadoNumDocumento' : ?0, 'fecha' : { $gte: ?1 } }")
    List<Cita> buscarCitasFuturasPorAfiliado(int numDocumento, Date desde);

    // Buscar citas disponibles por médico
    @Query("{ 'medicoRegistro' : ?0, 'estadoCita' : 'Disponible' }")
    List<Cita> buscarDisponiblesPorMedico(int medicoRegistro);
}
