package at.htl.boundary;

import at.htl.entity.Unit;
import at.htl.repository.UnitRepository;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("unit")
@Tag(name = "Unit REST endpoint")
public class UnitResource {

    @Inject
    UnitRepository unitRepository;

    @GET
    @Operation(
            summary = "get a unit",
            description = "get the desired unit by id"
    )
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
    @Operation(
            summary = "save a unit",
            description = "save the desired unit object"
    )
    public Response addUnit(Unit unit){
        return Response
                .accepted(unitRepository.save(unit))
                .build();
    }

    @DELETE
    @Operation(
            summary = "delete a unit",
            description = "delete the desired unit by id"
    )
    public Response deleteUnitById(@QueryParam("id") Long unitId){
        return Response
                .accepted(unitRepository.deleteById(unitId))
                .build();
    }

}
