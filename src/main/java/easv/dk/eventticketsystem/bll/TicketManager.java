package easv.dk.eventticketsystem.bll;



import easv.dk.eventticketsystem.dal.ITicketDAO;
import easv.dk.eventticketsystem.dal.db.TicketDAODB;

import java.io.IOException;
import java.sql.SQLException;

public class TicketManager {

    private final ITicketDAO ticketDAO = new TicketDAODB();

    public void createTicket(int orderId, int ticketTypeId, int eventId, int quantity) throws IOException, SQLException {
        String uniqueCode = QRCodeManager.generateAndSaveQRCode(400, 400);
        ticketDAO.createTicket(orderId, ticketTypeId, eventId, quantity, uniqueCode);
    }

    public void deleteTicket(String uniqueCode) throws SQLException {
        ticketDAO.deleteTicket(uniqueCode);
    }

}