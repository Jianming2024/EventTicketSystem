package easv.dk.eventticketsystem.dal;


import easv.dk.eventticketsystem.be.Events;

import java.io.IOException;
import java.util.List;

public interface IEventsDAO {
    List<Events> getAllEvents() throws IOException;
}
