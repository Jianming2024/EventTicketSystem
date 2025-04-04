package easv.dk.eventticketsystem.dal.db;

import easv.dk.eventticketsystem.bll.UUIDGenerator;
import easv.dk.eventticketsystem.dal.ITicketDAO;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.UUID;

public class TicketDAODB implements ITicketDAO {
    private final DBConnection db = new DBConnection();

    @Override
    public void createTicket(int orderId, int ticketTypeId) {

        try {
            String uniqueCode = UUIDGenerator.generateAndSaveQRCode(300, 300);


            String sql = "INSERT INTO Ticket (order_id, ticket_type_id, unique_code) VALUES (?, ?, ?)";
            try (Connection conn = db.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
                ps.setInt(1, orderId);
                ps.setInt(2, ticketTypeId);
                ps.setString(3, uniqueCode);
                ps.executeUpdate();
            }
        } catch (IOException | SQLException e) {
            e.printStackTrace(); // Optional: log or show an alert here
        }


    }

    public void deleteTicket(String uniqueCode) {

        String sql = "DELETE FROM Ticket WHERE unique_code = ?";
        try (Connection conn = db.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, uniqueCode);
            int rows = ps.executeUpdate();
            System.out.println("🗑️ Deleted ticket with code: " + uniqueCode);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}