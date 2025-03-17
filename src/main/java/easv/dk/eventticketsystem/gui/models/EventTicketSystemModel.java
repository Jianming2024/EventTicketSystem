package easv.dk.eventticketsystem.gui.models;

import easv.dk.eventticketsystem.be.TicketOnOrder;
import easv.dk.eventticketsystem.bll.TicketOnOrderManager;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.List;

public class EventTicketSystemModel {
    private final TicketOnOrderManager ticketOnOrderManager = new TicketOnOrderManager();
    private final ObservableList<TicketOnOrder> ticketOnOrders = FXCollections.observableArrayList();

    public ObservableList<TicketOnOrder> getAllOrderDetails() {
        List<TicketOnOrder> orderDetails = ticketOnOrderManager.getAllOrderDetails();
        ticketOnOrders.setAll(orderDetails);
        return ticketOnOrders;
    }
}
