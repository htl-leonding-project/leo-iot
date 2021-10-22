package at.htl.boundary;

import at.htl.entity.Sensor;
import at.htl.repository.SensorRepository;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;
import org.jboss.logging.annotations.Pos;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("sensor")
@Tag(name = "Sensor REST endpoint")
public class SensorResource {

    @Inject
    SensorRepository sensorRepository;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(
            summary = "get a sensor",
            description = "get the desired sensor by id"
    )
    public Sensor getSensor(@QueryParam("id") Long sensorId){
        return sensorRepository.findById(sensorId);
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Operation(
            summary = "save a sensor",
            description = "save the desired sensor object"
    )
    public Response addSensor(Sensor sensor){
        return Response
                .accepted(sensorRepository.save(sensor))
                .build();
    }

    @DELETE
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(
            summary = "delete a sensor",
            description = "delete the desired sensor by id"
    )
    public Response deleteSensorById(@QueryParam("id") Long sensorId){
        return Response
                .accepted(sensorRepository.deleteById(sensorId))
                .build();
    }
}
