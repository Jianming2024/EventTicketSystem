package easv.dk.eventticketsystem.dal.db;

import easv.dk.eventticketsystem.be.TicketOnOrder;
import easv.dk.eventticketsystem.dal.ITicketOnOrderDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TicketOnOrderDAODB implements ITicketOnOrderDAO {
    private DBConnection con = new DBConnection();

    @Override
    public List<TicketOnOrder> getAllOrderDetails() {
        List<TicketOnOrder> orderDetails = new ArrayList<>();
        String sql = """
            SELECT
                o.order_id,
                c.customer_name,
                c.customer_email,
                e.event_name,
                t.ticket_id,
                tt.type_name AS ticket_type,
                t.unique_code AS code
            FROM Orders o
            JOIN Customer c ON o.customer_id = c.customer_id
            JOIN Event e ON o.event_id = e.event_id
            JOIN Ticket t ON t.order_id = o.order_id
            JOIN Ticket_Type tt ON tt.ticket_type_id = t.ticket_type_id
            ORDER BY o.order_id DESC
            """;
        try (Connection connection = con.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int orderId         = rs.getInt("order_id");
                String customerName = rs.getString("customer_name");
                String customerEmail= rs.getString("customer_email");
                String eventName    = rs.getString("event_name");
                int ticketId        = rs.getInt("ticket_id");
                String ticketType   = rs.getString("ticket_type");
                String code         = rs.getString("code");
                System.out.println("Retrieving order id: " + orderId);
                TicketOnOrder too = new TicketOnOrder(orderId, customerName, customerEmail, eventName, ticketId, ticketType, code);
                orderDetails.add(too);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return orderDetails;
    }
}
