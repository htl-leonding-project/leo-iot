package at.htl.boundary;

import at.htl.repository.SensorRepository;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("unit")
public class UnitResource {

    @Inject
    SensorRepository sensorRepository;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllSensors(){
        return Response.accepted(sensorRepository.findAll()).build();
    }
}
