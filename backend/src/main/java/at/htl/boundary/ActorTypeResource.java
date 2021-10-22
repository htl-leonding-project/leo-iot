package at.htl.boundary;

import at.htl.entity.ActorType;
import at.htl.entity.Thing;
import at.htl.repository.ActorTypeRepository;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("actortype")
@Tag(name = "ActorType REST endpoint ")
public class ActorTypeResource {

    @Inject
    ActorTypeRepository actorTypeRepository;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(
            summary ="get an actortype",
            description = "get the desired actortype by id"
    )
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
    @Operation(
            summary = "save an actortype",
            description = "save the desired actortype"
    )
    public Response addActorType(ActorType actorType){
        return Response
                .accepted(actorTypeRepository.save(actorType))
                .build();
    }

    @DELETE
    @Operation(
            summary = "delete an actortype",
            description = "delte an desired actortype by id"
    )
    public Response deleteActorTypeById(@QueryParam("id") Long actorTypeId){
        return Response
                .accepted(actorTypeRepository.deleteById(actorTypeId))
                .build();
    }

}
