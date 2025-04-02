package easv.dk.eventticketsystem.bll;



import easv.dk.eventticketsystem.dal.ITicketDAO;
import easv.dk.eventticketsystem.dal.db.TicketDAODB;

import java.sql.SQLException;

public class TicketManager {

    private final ITicketDAO ticketDAO = new TicketDAODB();

    public void createTicket(int orderId, int ticketTypeId) throws SQLException {
        ticketDAO.createTicket(orderId, ticketTypeId);
    }

    public void deleteTicket(String uniqueCode) throws SQLException {
        ticketDAO.deleteTicket(uniqueCode);
    }

}

