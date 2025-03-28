package easv.dk.eventticketsystem.dal;


import easv.dk.eventticketsystem.be.Event;

import java.io.IOException;
import java.util.List;

public interface IEventDAO {
    List<Event> getAllEvents() throws IOException;
}
