package easv.dk.eventticketsystem.bll;
import easv.dk.eventticketsystem.be.Event;
import easv.dk.eventticketsystem.dal.IEventDAO;
import easv.dk.eventticketsystem.dal.db.EventDAODB;
import easv.dk.eventticketsystem.be.Users;

import java.io.IOException;
import java.util.List;

public class EventManager {
    private final IEventDAO eventsDAO = new EventDAODB();

    public List<Event> getAllEvents() throws IOException {
        return eventsDAO.getAllEvents();
    }
    public void createNewEvent(Event event) throws IOException {



        eventsDAO.createNewEvent(event);


    }





    public void deleteUsers(Event event) throws IOException {


        eventsDAO.deleteEvent(event);


    }





    public void updateEvent(Event event) throws IOException {


        eventsDAO.updateEvent(event);


    }
}

