package at.htl.mqtt.client.boundary;

import at.htl.mqtt.client.repository.RoomRepository;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Path("/api")
public class ConfigEndpoint {

    @Inject
    RoomRepository roomRepo;

    @POST
    @Path("addRoom/{roomName}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public boolean addRoom(@PathParam("roomName") String roomName) {
        return roomRepo.addRoom(roomName);
    }
}