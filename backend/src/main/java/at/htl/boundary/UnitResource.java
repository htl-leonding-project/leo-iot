package at.htl.boundary;

import at.htl.entity.Unit;
import at.htl.repository.UnitRepository;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
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
            return Response
                    .accepted(unitRepository.findAll())
                    .build();
        }
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addUnit(Unit unit){
        return Response
                .accepted(unitRepository.save(unit))
                .build();
    }

    @DELETE
    public Response deleteUnitById(@QueryParam("id") Long unitId){
        return Response
                .accepted(unitRepository.deleteById(unitId))
                .build();
    }

}
