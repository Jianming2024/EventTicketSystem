package easv.dk.eventticketsystem.dal;

public interface ITicketDAO {
    void createTicket(int orderId, int ticketTypeId);
    void deleteTicket(String uniqueCode);

}
