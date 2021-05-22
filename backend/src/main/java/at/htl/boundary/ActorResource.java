package at.htl.boundary;

import at.htl.repository.ActorRepository;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;

@Path("actor")
public class ActorResource {

    @Inject
    ActorRepository actorRepository;

    @GET
    public Response getActor(@QueryParam("id") Long actorType){
        if(actorType != null){
            return Response
                    .accepted(actorRepository.findById(actorType))
                    .build();
        }else {
            return Response
                    .accepted(actorRepository.findAll())
                    .build();
        }
    }

}
