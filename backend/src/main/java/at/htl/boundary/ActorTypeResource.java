package at.htl.boundary;

import at.htl.repository.ActorTypeRepository;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;

@Path("actortype")
public class ActorTypeResource {

    @Inject
    ActorTypeRepository actorTypeRepository;

    @GET
    public Response getActorType(@QueryParam("id") Long actorTypeId){
        if (actorTypeId != null){
            return Response
                    .accepted(actorTypeRepository.findById(actorTypeId))
                    .build();
        }else {
            return Response
                    .accepted(actorTypeRepository.findAll())
                    .build();
        }
    }
}
