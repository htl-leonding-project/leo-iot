package at.htl.boundary;

import at.htl.repository.MeasurementRepository;
import at.htl.repository.SensorRepository;
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
        return null;
    }

}
