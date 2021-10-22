package at.htl.boundary;

import at.htl.entity.Location;
import at.htl.entity.Thing;
import at.htl.repository.ThingRepository;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("thing")
@Tag(name = "Thing REST endpoint")
public class ThingResource {

    @Inject
    ThingRepository thingRepository;

    @GET

    public Response getThing(@QueryParam("id") Long thingId){
        if(thingId != null) {
            return Response
                    .accepted(thingRepository.findById(thingId))
                    .build();
        }else {
            return  Response
                    .accepted(thingRepository.findAll())
                    .build();
        }
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addThing(Thing thing){
        return Response
                .accepted(thingRepository.save(thing))
                .build();
    }

    @DELETE
    public Response deleteThingById(@QueryParam("id") Long thingId){
        return Response
                .accepted(thingRepository.deleteById(thingId))
                .build();
    }

}
