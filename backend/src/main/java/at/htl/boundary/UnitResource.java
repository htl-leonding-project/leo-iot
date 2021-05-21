package at.htl.boundary;

import at.htl.entity.Sensor;
import at.htl.repository.SensorRepository;
import org.jboss.logging.annotations.Pos;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("unit")
public class UnitResource {

    @Inject
    SensorRepository sensorRepository;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getSensor(@QueryParam("id") Long sensorId){
        if (sensorId != null) {
            return Response.accepted(sensorRepository.findById(sensorId)).build();
        }else{
            return Response.accepted(sensorRepository.findAll()).build();
        }
    }


    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addSensor(Sensor sensor){
        return Response.accepted(sensorRepository.save(sensor)).build();
    }

    @DELETE
    public Response deleteSensorById(@QueryParam("id") Long sensorId){
        return Response.accepted(sensorRepository.deleteById(sensorId)).build();
    }

}
