package easv.dk.eventticketsystem.dal.db;

import easv.dk.eventticketsystem.be.Customer;

import java.sql.*;

public class CustomerDAODB {

    private final DBConnection con = new DBConnection();

    public Customer getCustomerByEmail(String email) throws SQLException {
        String sql = "SELECT * FROM Customer WHERE customer_email = ?";
        try (Connection connection = con.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, email);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return new Customer(
                        rs.getInt("customer_id"),
                        rs.getString("customer_name"),
                        rs.getString("customer_email"),
                        rs.getString("customer_phone")
                );
            }
        }
        return null;
    }

    public int createCustomer(Customer customer) throws SQLException {
        String sql = "INSERT INTO Customer (customer_name, customer_email) VALUES (?, ?)";
        try (Connection connection = con.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, customer.getCustomerName());
            ps.setString(2, customer.getCustomerEmail());
            ps.executeUpdate();

            ResultSet keys = ps.getGeneratedKeys();
            if (keys.next()) {
                return keys.getInt(1);
            }
        }
        return -1;
    }
}
