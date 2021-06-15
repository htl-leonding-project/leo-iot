package at.htl.boundary;

import at.htl.entity.ActorType;
import at.htl.entity.Thing;
import at.htl.repository.ActorTypeRepository;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
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

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addActorType(ActorType actorType){
        return Response
                .accepted(actorTypeRepository.save(actorType))
                .build();
    }

    @DELETE
    public Response deleteActorTypeById(@QueryParam("id") Long actorTypeId){
        return Response
                .accepted(actorTypeRepository.deleteById(actorTypeId))
                .build();
    }

}
