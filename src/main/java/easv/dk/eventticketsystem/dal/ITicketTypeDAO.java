package easv.dk.eventticketsystem.dal;

import easv.dk.eventticketsystem.be.TicketType;

import java.util.List;

public interface ITicketTypeDAO {
    TicketType createTicketType(TicketType ticketType) throws Exception;
    List<TicketType> getAllTicketTypes() throws Exception;

}
