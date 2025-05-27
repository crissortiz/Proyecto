package uniandes.edu.co.proyecto.repositorio;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.Update;

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
    // Buscar citas disponibles por servicio
    @Query("{ 'ordenServicioDescripcion' : ?0, 'estadoCita' : 'Disponible' }")
    List<Cita> buscarDisponiblesPorServicio(String servicio);

    //Marcar una cita como ocupada por su ID
    @Query("{ '_id': ?0 }")
    @Update("{ '$set': { 'estadoCita': 'Ocupada' } }")
    void marcarCitaComoOcupada(String id);
    //Buscar citas disponibles por servicio y fecha
    @Query("{ 'servicioId' : ?0, 'estadoCita' : 'Disponible', 'fecha' : { $gte: ?1, $lte: ?2 } }")
    List<Cita> buscarDisponiblesPorServicioYFecha(int servicioId, Date desde, Date hasta);
}
