package easv.dk.eventticketsystem.gui.model;

import easv.dk.eventticketsystem.be.Event;
import easv.dk.eventticketsystem.be.TicketOnOrder;
import easv.dk.eventticketsystem.be.Users;
import easv.dk.eventticketsystem.bll.EventManager;
import easv.dk.eventticketsystem.bll.TicketOnOrderManager;
import easv.dk.eventticketsystem.bll.UsersManager;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.IOException;
import java.util.List;

public class EventTicketSystemModel {
    private final TicketOnOrderManager ticketOnOrderManager = new TicketOnOrderManager();
    private final UsersManager usersManager = new UsersManager();
    private final EventManager eventManager = new EventManager();
    private final ObservableList<TicketOnOrder> ticketOnOrders = FXCollections.observableArrayList();
    private final ObservableList<Users> allUsers = FXCollections.observableArrayList();
    private final ObservableList<Event> allEvents = FXCollections.observableArrayList();


    public ObservableList<TicketOnOrder> getAllOrderDetails() {
        List<TicketOnOrder> orderDetails = ticketOnOrderManager.getAllOrderDetails();
        ticketOnOrders.setAll(orderDetails);
        return ticketOnOrders;
    }

    public ObservableList<Users> getAllUsers() throws IOException {
        List<Users> usersList = usersManager.getAllUsers();
        allUsers.setAll(usersList);
        return allUsers;
    }

    public ObservableList<Event> getAllEvents() throws IOException {
        List<Event> eventList = eventManager.getAllEvents();
        allEvents.setAll(eventList);
        return allEvents;
    }
}
