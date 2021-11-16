package at.htl.mqtt.client.repository;

import at.htl.mqtt.client.boundary.MyValueGenerator;
import at.htl.mqtt.client.entity.Room;
import io.quarkus.hibernate.orm.panache.PanacheRepository;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.LinkedList;
import java.util.List;

@ApplicationScoped
public class RoomRepository implements PanacheRepository<Room>
{
    @Inject
    MyValueGenerator myValueGenerator;

    List<Room> rooms = new LinkedList();


    public List getAllRooms() {
        return rooms;
    }

    @Transactional
    public boolean addRoom(String roomName) {
        Room currRoom = new Room(roomName);

        if(rooms.stream().anyMatch(r -> r.getName().equals(roomName)))
        {
            System.out.println("Room already exists");
            return false;
        }

        persist(currRoom);

        boolean returnValue = rooms.add(currRoom);

        myValueGenerator.roomData(currRoom);

        return returnValue;
    }

    public boolean deleteRoom(String roomName) {
        for (Room room: rooms) {
            if (room.getName().equals(roomName))
            {
                rooms.remove(room);
                myValueGenerator.stop();
                myValueGenerator.getAllRooms();
                return true;
            }
        }
        return false;
    }
}
