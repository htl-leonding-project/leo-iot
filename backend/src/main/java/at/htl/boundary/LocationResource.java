package at.htl.boundary;

import at.htl.entity.Location;
import at.htl.entity.Sensor;
import at.htl.repository.LocationRepository;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("location")
public class LocationResource {

    @Inject
    LocationRepository locationRepository;

    @GET
    public Response getLocation(@QueryParam("id") Long locationId){
        if (locationId != null){
            return Response
                    .accepted(locationRepository.findById(locationId))
                    .build();
        }else{
            return Response
                    .accepted(locationRepository.findAll())
                    .build();
        }
    }
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addLocation(Location location){
        return Response
                .accepted(locationRepository.save(location))
                .build();
    }


}
