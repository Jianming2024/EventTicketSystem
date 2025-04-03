package easv.dk.eventticketsystem.dal.db;

import easv.dk.eventticketsystem.be.TicketType;
import easv.dk.eventticketsystem.dal.ITicketTypeDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class TicketTypeDAODB implements ITicketTypeDAO {

    private final DBConnection con = new DBConnection();
    @Override
    public void createTicketType(TicketType ticketType) throws Exception {
        String sql = "INSERT INTO Ticket_Type (type_name, type_category, price) VALUES (?, ?, ?)";
        try (Connection conn = con.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, ticketType.getTypeName());
            ps.setString(2, ticketType.getCategory());
            ps.setDouble(3, ticketType.getPrice());
            ps.executeUpdate();
        }
    }

    @Override
    public List<TicketType> getAllTicketTypes() throws Exception {
        List<TicketType> list = new ArrayList<>();
        String sql = "SELECT * FROM Ticket_Type";

        try (Connection conn = con.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("ticket_type_id");
                String name = rs.getString("type_name");
                String category = rs.getString("type_category");
                double price = rs.getDouble("price");

                list.add(new TicketType(id, name, category, price));
            }
        }
        return list;
    }
}
