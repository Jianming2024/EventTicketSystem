package easv.dk.eventticketsystem.dal.db;

import com.microsoft.sqlserver.jdbc.SQLServerException;
import easv.dk.eventticketsystem.be.Users;
import easv.dk.eventticketsystem.dal.IUsersDAO;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UsersDAODB implements IUsersDAO {
    private DBConnection con = new DBConnection();

    @Override
    public List<Users> getAllUsers() throws IOException {
        List<Users> allUsers = new ArrayList<>();
        String sql = "SELECT * FROM users";
        try (Connection connection = con.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)){
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int userId = rs.getInt("user_id");
                String userName = rs.getString("user_name");
                String role = rs.getString("role");
                String userEmail = rs.getString("user_email");
                String userPhone = rs.getString("user_phone");
                String userImagePath = rs.getString("user_image_path");
/*                System.out.println("DEBUG: userId = " + userId
                        + ", username = " + userName
                        + ", role = " + role
                        + ", email = " + userEmail
                        + ", phone = " + userPhone
                        + ", imagePath = " + userImagePath);*/
                Users users = new Users(userId, userName, userImagePath, role, userEmail, userPhone);
                allUsers.add(users);
            }
        } catch (SQLServerException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return allUsers;
    }
}
