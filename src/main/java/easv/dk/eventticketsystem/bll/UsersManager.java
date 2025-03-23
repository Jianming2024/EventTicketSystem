package easv.dk.eventticketsystem.bll;

import easv.dk.eventticketsystem.be.Users;
import easv.dk.eventticketsystem.dal.IUsersDAO;
import easv.dk.eventticketsystem.dal.db.UsersDAODB;

import java.io.IOException;
import java.util.List;

public class UsersManager {
    private final IUsersDAO usersDAO = new UsersDAODB();

    public List<Users> getAllUsers() throws IOException {
        return usersDAO.getAllUsers();
    }
}
