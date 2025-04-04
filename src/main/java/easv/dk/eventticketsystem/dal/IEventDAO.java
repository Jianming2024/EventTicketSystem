package easv.dk.eventticketsystem.dal;


import easv.dk.eventticketsystem.be.Event;
import easv.dk.eventticketsystem.be.Users;
import java.io.IOException;
import java.util.List;

public interface IEventDAO {
    List<Event> getAllEvents() throws IOException;

    void createNewEvent(Event event) throws IOException;

    void deleteEvent(Event event) throws IOException;

    void updateEvent(Event event) throws IOException;
}
