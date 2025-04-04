package easv.dk.eventticketsystem.bll;

import easv.dk.eventticketsystem.be.TicketType;
import easv.dk.eventticketsystem.dal.ITicketTypeDAO;
import easv.dk.eventticketsystem.dal.db.TicketTypeDAODB;

import java.sql.SQLException;
import java.util.List;

public class TicketTypeManager {

    private final ITicketTypeDAO ticketTypeDAO = new TicketTypeDAODB();

    public void createTicketType(TicketType ticketType) throws Exception {
        ticketTypeDAO.createTicketType(ticketType);
    }

    public List<TicketType> getAllTicketTypes() throws Exception {
        return ticketTypeDAO.getAllTicketTypes();
    }

    /// method used if wanting to delete TicketType field in ticketOnOrder class
//    public TicketType getTicketTypeById(int id) throws SQLException {
//        return ticketTypeDAO.getTicketTypeById(id);
//    }

}