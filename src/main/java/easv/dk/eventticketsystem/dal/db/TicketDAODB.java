package easv.dk.eventticketsystem.dal.db;

import easv.dk.eventticketsystem.bll.UUIDGenerator;
import easv.dk.eventticketsystem.dal.ITicketDAO;

import java.io.File;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.UUID;

public class TicketDAODB implements ITicketDAO {
    private final DBConnection db = new DBConnection();

    @Override
    public void createTicket(int orderId, int ticketTypeId) {

        String uniqueCode = UUID.randomUUID().toString();
        //Saving locally to avoid conflicts
        String qrDirectory = System.getProperty("user.dir") + "/qr_codes/";
        File dir = new File(qrDirectory);
        if (!dir.exists()) {
            dir.mkdirs();
        }

        String filePath = qrDirectory + uniqueCode + ".png";

        try {
            UUIDGenerator.generateQRCode(uniqueCode, filePath, 300, 300);
        } catch (Exception e) {
            e.printStackTrace(); // optional: show alert or log
        }

        System.out.println("Saving QR to: " + filePath);
        String sql = "INSERT INTO Ticket (order_id, ticket_type_id, unique_code) VALUES (?, ?, ?)";
        try (Connection conn = db.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, orderId);
            ps.setInt(2, ticketTypeId);
            ps.setString(3, uniqueCode);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace(); // Optional: log or show an alert here
        }}
    }

