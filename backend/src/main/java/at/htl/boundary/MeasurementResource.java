package at.htl.boundary;

import at.htl.entity.Measurement;
import at.htl.entity.Sensor;
import at.htl.repository.MeasurementRepository;
import at.htl.repository.SensorRepository;
import org.eclipse.microprofile.reactive.messaging.Incoming;

import javax.inject.Inject;
import javax.json.JsonObject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.sql.Timestamp;

@Path("/measurement")
public class MeasurementResource {

    @Inject
    MeasurementRepository measurementRepository;

    @Inject
    SensorRepository sensorRepository;

    @GET
    public Response getMeasurement(@QueryParam("to") Long to, @QueryParam("from") Long from, @QueryParam("sensor") Long sensorId){
        if(to != null && from != null && sensorId == null){
         return Response
                 .accepted(measurementRepository.get(new Timestamp(from * 1000), new Timestamp(to * 1000)))
                 .build();
        }else
        if (to != null && from != null && sensorId != null){
            return Response
                    .accepted(measurementRepository.get(new Timestamp(from * 1000), new Timestamp(to * 1000), sensorRepository.findById(sensorId)))
                    .build();
        }
        else return null;
    }

    @POST
    @Path("/add-measurement")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response addMeasurement(@QueryParam("sensorId") Long sensorId ,JsonObject jsonObject){
        Measurement measurement = new Measurement();
        measurement.setValue(jsonObject.getInt("value"));
        Measurement.MeasurementKey measurementKey =
                new Measurement.MeasurementKey(new Timestamp(jsonObject.getJsonObject("measurementKey").getInt("timestamp") * 1000),new Sensor());
        measurementKey.setSensor(sensorRepository.findById(sensorId));
        measurement.setMeasurementKey(measurementKey);
        return Response.accepted().build();
    }

    @DELETE
    @Path("/remove-measurement")
    public Response removeMeasurement(Measurement measurement ){
        measurementRepository.remove(measurement);
        return Response.accepted(measurement).build();
    }

}
