package at.htl.boundary;

import at.htl.entity.Location;
import at.htl.entity.SensorType;
import at.htl.repository.SensorTypeRepository;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("sensortype")
public class SensorTypeResource {

    @Inject
    SensorTypeRepository sensorTypeRepository;

    @GET
    public Response getSensorType(@QueryParam("id") Long sensorTypeId){
        if (sensorTypeId != null){
            return Response
                    .accepted(sensorTypeRepository.findById(sensorTypeId))
                    .build();
        }else {
            return Response
                    .accepted(sensorTypeRepository.findAll())
                    .build();
        }
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addSensorType(SensorType sensorType){
        return Response
                .accepted(sensorTypeRepository.save(sensorType))
                .build();
    }

}
