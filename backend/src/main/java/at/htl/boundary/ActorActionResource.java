package at.htl.boundary;

import at.htl.repository.ActorActionRepository;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;

@Path("actoraction")
public class ActorActionResource {

    @Inject
    ActorActionRepository actorActionRepository;

    @GET
    public Response getActorAction(@QueryParam("id") Long actorActionId){
        if (actorActionId != null){
            return  Response.accepted().build();
        }else{
            return Response
                    .accepted(actorActionRepository.findAll())
                    .build();
        }
    }
}
