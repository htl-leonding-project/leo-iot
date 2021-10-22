package at.htl.boundary;

import at.htl.entity.Location;
import at.htl.entity.Sensor;
import at.htl.repository.LocationRepository;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("location")
@Tag(name ="Location REST endpoint")
public class LocationResource {

    @Inject
    LocationRepository locationRepository;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(
            summary = "get a location",
            description = "get the desired location by id"
    )
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
    @Operation(
            summary = "save a location",
            description = "save the desired location"
    )
    public Response addLocation(Location location){
        return Response
                .accepted(locationRepository.save(location))
                .build();
    }

    @DELETE
    @Operation(
            summary =  "delete a location",
            description = "delete the desired location"
    )
    public Response deleteLocationById(@QueryParam("id") Long locationId){
        return Response
                .accepted(locationRepository.deleteById(locationId))
                .build();
    }

}
