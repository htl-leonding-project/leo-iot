package at.htl.boundary;

import at.htl.repository.LocationRepository;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
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

}
