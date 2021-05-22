package at.htl.boundary;

import at.htl.entity.Actor;
import at.htl.entity.ActorType;
import at.htl.repository.ActorRepository;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("actor")
public class ActorResource {

    @Inject
    ActorRepository actorRepository;

    @GET
    public Response getActor(@QueryParam("id") Long actorId){
        if(actorId != null){
            return Response
                    .accepted(actorRepository.findById(actorId))
                    .build();
        }else {
            return Response
                    .accepted(actorRepository.findAll())
                    .build();
        }
    }
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addActor(Actor actor){
        return Response
                .accepted(actorRepository.save(actor))
                .build();
    }
}
