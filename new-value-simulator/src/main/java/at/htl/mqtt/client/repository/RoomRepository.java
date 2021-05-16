package at.htl.mqtt.client.repository;

import at.htl.mqtt.client.entity.Room;
import io.quarkus.hibernate.orm.panache.PanacheRepository;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.LinkedList;
import java.util.List;

public class RoomRepository
{

    List<Room> rooms = new LinkedList();


    public List getAllRooms() {
        return rooms;
    }
}
