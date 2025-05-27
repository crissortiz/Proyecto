package uniandes.edu.co.proyecto.repositorio;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.Update;
import uniandes.edu.co.proyecto.modelo.Afiliado;

import java.util.List;

public interface AfiliadoRepository extends MongoRepository<Afiliado, String> {

    // Buscar por número de documento
    @Query("{ 'numDocumento' : ?0 }")
    List<Afiliado> buscarPorNumDocumento(int numDocumento);

    // Insertar un afiliado usando default (opcional)
    default void insertarAfiliado(Afiliado afiliado) {
        save(afiliado);
    }

    // Actualizar datos de afiliado por número de documento
    @Query(value = "{ 'numDocumento' : ?0 }")
    @Update("{ '$set' : { 'nombre' : ?1, 'direccion' : ?2, 'telefono' : ?3 } }")
    void actualizarAfiliado(int numDocumento, String nombre, String direccion, String telefono);

    // Eliminar por número de documento
    @Query(value = "{ 'numDocumento' : ?0 }", delete = true)
    void eliminarPorNumDocumento(int numDocumento);
}
