package easv.dk.eventticketsystem.bll;
import easv.dk.eventticketsystem.be.Event;
import easv.dk.eventticketsystem.dal.IEventDAO;
import easv.dk.eventticketsystem.dal.db.EventDAODB;

import java.io.IOException;
import java.util.List;

public class EventManager {
    private final IEventDAO eventsDAO = new EventDAODB();

    public List<Event> getAllEvents() throws IOException {
        return eventsDAO.getAllEvents();
    }
}

