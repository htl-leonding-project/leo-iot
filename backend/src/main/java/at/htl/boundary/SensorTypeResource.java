package at.htl.boundary;

import at.htl.repository.SensorTypeRepository;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
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

}
