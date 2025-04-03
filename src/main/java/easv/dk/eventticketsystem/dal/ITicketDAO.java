package easv.dk.eventticketsystem.dal;

import java.sql.SQLException;

public interface ITicketDAO {
    void createTicket(int orderId, int ticketTypeId,int eventId,int quantity, String uniqueCode) throws SQLException;
    void deleteTicket(String uniqueCode);

}
