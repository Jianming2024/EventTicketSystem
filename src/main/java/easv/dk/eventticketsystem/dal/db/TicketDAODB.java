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
    private final DBConnection con = new DBConnection();

    @Override
    public void createTicket(int orderId, int ticketTypeId,int eventId,int quantity, String uniqueCode) {

        try {


            String sql = "INSERT INTO Ticket (order_id, ticket_type_id, event_id, unique_code , quantity) VALUES (?, ?, ?, ?,?)";
            try (Connection conn = con.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
                ps.setInt(1, orderId);
                ps.setInt(2, ticketTypeId);
                ps.setInt(3, eventId);
                ps.setString(4, uniqueCode);
                ps.setInt(5, quantity);
                ps.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }


    }

    public void deleteTicket(String uniqueCode) {

        String sql = "DELETE FROM Ticket WHERE unique_code = ?";
        try (Connection conn = con.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, uniqueCode);
            int rows = ps.executeUpdate();
            System.out.println("🗑️ Deleted ticket with code: " + uniqueCode);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}