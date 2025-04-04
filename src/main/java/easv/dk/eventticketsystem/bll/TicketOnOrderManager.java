package easv.dk.eventticketsystem.bll;

import easv.dk.eventticketsystem.be.TicketOnOrder;
import easv.dk.eventticketsystem.dal.ITicketOnOrderDAO;
import easv.dk.eventticketsystem.dal.db.TicketOnOrderDAODB;

import java.util.List;

public class TicketOnOrderManager {
    private final ITicketOnOrderDAO ticketOnOrderDAO = new TicketOnOrderDAODB();

    public List<TicketOnOrder> getAllOrderDetails() {
        return ticketOnOrderDAO.getAllOrderDetails();
    }
}
