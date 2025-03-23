package easv.dk.eventticketsystem.dal;

import easv.dk.eventticketsystem.be.TicketOnOrder;
import easv.dk.eventticketsystem.be.Users;

import java.io.IOException;
import java.util.List;

public interface IUsersDAO {
    List<Users> getAllUsers() throws IOException;
}
