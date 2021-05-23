package at.htl.boundary;

import at.htl.entity.ActorAction;
import at.htl.repository.ActorActionRepository;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;

@Path("actoraction")
public class ActorActionResource {

    @Inject
    ActorActionRepository actorActionRepository;

    @GET
    public Response getAllActorAction(){
        return Response.accepted(actorActionRepository.findAll()).build();
    }

    @POST
    public Response addActorAction(ActorAction actorAction){
        return Response.accepted( actorActionRepository.save(actorAction)).build();
    }

    @DELETE
    public Response removeActorAction(ActorAction actorAction){
        actorActionRepository.delete(actorAction);
        return Response.accepted(actorAction).build();
    }
}
