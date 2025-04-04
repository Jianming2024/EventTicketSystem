package easv.dk.eventticketsystem.dal;

import easv.dk.eventticketsystem.be.TicketOnOrder;
import easv.dk.eventticketsystem.be.Users;

import java.io.IOException;
import java.util.List;

public interface IUsersDAO {
    List<Users> getAllUsers() throws IOException;

    void createNewUsers(Users users) throws IOException;

    void deleteUsers(Users users) throws IOException;

    void updateUsers(Users users) throws IOException;
}
