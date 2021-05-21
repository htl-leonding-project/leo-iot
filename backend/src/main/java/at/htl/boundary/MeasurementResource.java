package at.htl.boundary;

import at.htl.repository.MeasurementRepository;
import org.eclipse.microprofile.reactive.messaging.Incoming;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;
import java.sql.Timestamp;

@Path("/measurement")
public class MeasurementResource {

    @Inject
    MeasurementRepository measurementRepository;

    @GET
    public Response getMeasurement(@QueryParam("to") String to, @QueryParam("from") String from, @QueryParam("sensor") Long sensorId){
        if(to != null && from != null && sensorId == null){
            //measurementRepository.get()
         return Response.accepted(to).build();
        }else
            if (to != null && from != null && sensorId != null){
                //return measurementRepository.get()
            }
        return null;
    }

    public Timestamp tinmestampParser(String data){

        return null;
    }

}
