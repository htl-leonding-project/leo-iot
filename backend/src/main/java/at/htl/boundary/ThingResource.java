package at.htl.boundary;

import at.htl.repository.ThingRepository;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;

@Path("")
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

}
