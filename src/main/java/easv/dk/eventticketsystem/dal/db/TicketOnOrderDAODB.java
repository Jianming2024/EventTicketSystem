package easv.dk.eventticketsystem.dal.db;

import easv.dk.eventticketsystem.be.TicketOnOrder;
import easv.dk.eventticketsystem.dal.ITicketOnOrderDAO;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TicketOnOrderDAODB implements ITicketOnOrderDAO {
    private DBConnection con = new DBConnection();

    @Override
    //This method gets all order details with status = "pending"
    public List<TicketOnOrder> getAllOrderDetails() {
        List<TicketOnOrder> orderDetails = new ArrayList<>();
        String sql = """
                   SELECT
                    o.order_id,
                    c.customer_name,
                    c.customer_email,
                    e.event_name,
                    e.start_datetime,
                    e.location,
                    e.price,
                    t.quantity,
                    t.ticket_id AS ticket_id,
                    tt.type_name AS ticket_type,
                    t.unique_code AS code
                   
                FROM Orders o
                JOIN Customer c ON o.customer_id = c.customer_id
                          JOIN Ticket t ON t.order_id = o.order_id
                          JOIN Event e ON t.event_id = e.event_id
                JOIN Ticket_Type tt ON tt.ticket_type_id = t.ticket_type_id
                WHERE o.status = 'Pending'
                ORDER BY o.order_id DESC               
                """;
        try (Connection connection = con.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int orderId = rs.getInt("order_id");
                String customerName = rs.getString("customer_name");
                String customerEmail = rs.getString("customer_email");
                String eventName = rs.getString("event_name");
                int ticketId = rs.getInt("ticket_id");
                String ticketType = rs.getString("ticket_type");
                String code = rs.getString("code");
                String startDateTime = rs.getString("start_datetime");
                String location = rs.getString("location");
                String price = rs.getString("price");
                int quantity = rs.getInt("quantity");
                System.out.println("Retrieving order id: " + orderId);

                // Convert string to LocalDateTime
                LocalDateTime dateTime = LocalDateTime.parse(startDateTime.replace(" ", "T"));

                // Format date and time
                String eventDate = dateTime.toLocalDate().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
                String eventTime = dateTime.toLocalTime().format(DateTimeFormatter.ofPattern("HH:mm"));


                TicketOnOrder too = new TicketOnOrder(orderId, customerName, customerEmail, eventName, ticketId, ticketType, code, eventDate, eventTime, location, price, quantity);
                orderDetails.add(too);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return orderDetails;
    }

    // Create a method to getallOrderConfirmed = To retrieve old orders if needed
}
