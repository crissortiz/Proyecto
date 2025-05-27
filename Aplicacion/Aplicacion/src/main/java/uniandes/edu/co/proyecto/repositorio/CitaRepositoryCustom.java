package uniandes.edu.co.proyecto.repositorio;

import java.util.*;
import java.util.Date;

import org.bson.Document;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class CitaRepositoryCustom {

    private final MongoTemplate mongoTemplate;

    public CitaRepositoryCustom(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    /**
     * Retorna los 20 servicios más solicitados en un rango de fechas.
     *
     * @param fechaInicio fecha inicial del periodo a consultar
     * @param fechaFin fecha final del periodo a consultar
     * @return Lista de documentos con el nombre del servicio y cantidad de veces solicitado
     */
    public List<Document> obtenerServiciosMasSolicitados(Date fechaInicio, Date fechaFin) {
        List<Document> pipeline = List.of(
            new Document("$match", new Document("fecha", new Document("$gte", fechaInicio).append("$lte", fechaFin))),
            new Document("$group", new Document("_id", "$ordenServicioDescripcion").append("total", new Document("$sum", 1))),
            new Document("$sort", new Document("total", -1)),
            new Document("$limit", 20)
        );

        return mongoTemplate.getCollection("citas").aggregate(pipeline).into(new ArrayList<>());
    }
}
