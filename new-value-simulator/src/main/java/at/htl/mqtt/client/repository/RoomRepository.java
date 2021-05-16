package at.htl.mqtt.client.repository;

import at.htl.mqtt.client.entity.Room;
import java.util.LinkedList;
import java.util.List;

public class RoomRepository
{

    List<Room> rooms = new LinkedList();


    public List getAllRooms() {
        return rooms;
    }

    public void addRoom(String roomName) {
        rooms.add(new Room(roomName));
    }
}
