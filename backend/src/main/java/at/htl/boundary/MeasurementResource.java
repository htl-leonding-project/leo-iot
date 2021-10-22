package at.htl.boundary;

import at.htl.entity.Measurement;
import at.htl.entity.Sensor;
import at.htl.repository.MeasurementRepository;
import at.htl.repository.SensorRepository;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;
import org.eclipse.microprofile.reactive.messaging.Incoming;

import javax.inject.Inject;
import javax.json.JsonObject;
import javax.persistence.OrderBy;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.sql.Timestamp;

@Path("/measurement")
@Tag(name="Measurement REST endpoint")
public class MeasurementResource {

    @Inject
    MeasurementRepository measurementRepository;

    @Inject
    SensorRepository sensorRepository;

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Operation(
            summary = "save a measurement",
            description = "save the desired measurement with id"
    )
    public Response addMeasurement(@QueryParam("sensorId") Long sensorId ,JsonObject jsonObject){
        Measurement measurement = new Measurement();
        measurement.setValue(jsonObject.getInt("value"));
        Measurement.MeasurementKey measurementKey =
                new Measurement.MeasurementKey(new Timestamp(jsonObject.getJsonObject("measurementKey").getInt("timestamp") * 1000),new Sensor());

        measurementKey.setSensor(sensorRepository.findById(sensorId));
        measurement.setMeasurementKey(measurementKey);
        return Response.accepted(measurementRepository.save(measurement)).build();
    }

    @DELETE
    @Path("/remove-measurement")
    @Operation(
            summary = "delete a measurement",
            description = "remove the desired measurement object"
    )
    public Response removeMeasurement(Measurement measurement ){
        measurementRepository.remove(measurement);
        return Response.accepted(measurement).build();
    }

    @GET
    @Operation(
            summary = "get a measurement",
            description = "get thr desired measurement by timestamp"
    )
    public Response getMeasurmentByTimestamp(@QueryParam("timestamp") Long measurementTimestamp){
        if (measurementTimestamp != null){
            return Response
                    .accepted(measurementRepository
                            .getMeasurementByTimestamp(new Timestamp(measurementTimestamp * 1000)))
                    .build();
        }else{
            return Response
                    .accepted(measurementRepository.findAll())
                    .build();
        }
    }

}
