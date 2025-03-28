package easv.dk.eventticketsystem.bll;
import easv.dk.eventticketsystem.be.Events;
import easv.dk.eventticketsystem.dal.IEventsDAO;
import easv.dk.eventticketsystem.dal.db.EventsDAODB;

import java.io.IOException;
import java.util.List;

public class EventsManager {
    private final IEventsDAO eventsDAO = new EventsDAODB();

    public List<Events> getAllEvents() throws IOException {
        return eventsDAO.getAllEvents();
    }
}

