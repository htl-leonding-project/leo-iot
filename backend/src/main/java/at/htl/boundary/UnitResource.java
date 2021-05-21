package at.htl.boundary;

import at.htl.repository.UnitRepository;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;

@Path("unit")
public class UnitResource {

    @Inject
    UnitRepository unitRepository;

    @GET
    public Response getUnit(@QueryParam("id") Long unitId ){
        if(unitId != null){
            return Response
                    .accepted(unitRepository.findById(unitId))
                    .build();
        }else {
            return Response.accepted(unitRepository.findAll()).build();
        }
    }

}
