package easv.dk.eventticketsystem.dal.db;

import com.microsoft.sqlserver.jdbc.SQLServerException;
import easv.dk.eventticketsystem.be.Orders;
import easv.dk.eventticketsystem.dal.IOrderDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OrderDAODB {
    private DBConnection con = new DBConnection();

    public int getNextOrderId() {
        String sql = "SELECT ISNULL(MAX(order_id), 0) + 1 FROM Orders";
        try (Connection connection = con.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            if (rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }
    public void updateOrderStatus(int orderId, String status) throws SQLException {
        String sql = "UPDATE Orders SET status = ? WHERE order_id = ?";
        try (Connection conn = con.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, status);
            stmt.setInt(2, orderId);
            stmt.executeUpdate();
        }
    }



}
