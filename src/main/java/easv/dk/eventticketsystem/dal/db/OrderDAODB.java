package easv.dk.eventticketsystem.dal.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

// Class that Only handles raw SQL

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

    public void deleteOrder(int orderId) throws SQLException {

        try (Connection conn = con.getConnection()){

        String deleteTicketsSql = "DELETE FROM Ticket WHERE order_id = ?";
            try (PreparedStatement psTickets = conn.prepareStatement(deleteTicketsSql)) {
            psTickets.setInt(1, orderId);
            psTickets.executeUpdate();
        }

        String deleteOrderSql = "DELETE FROM Orders WHERE order_id = ?";

            try (PreparedStatement stmt = conn.prepareStatement(deleteOrderSql)) {
            stmt.setInt(1, orderId);
            stmt.executeUpdate();
        }
    }


    }
    public void updateCustomerId(int order_id, int customer_id) throws SQLException {
        String sql = "UPDATE Orders SET customer_id = ? WHERE order_id = ?";
        try (Connection connection = con.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, customer_id);
            ps.setInt(2, order_id);
            ps.executeUpdate();
        }
    }

}
