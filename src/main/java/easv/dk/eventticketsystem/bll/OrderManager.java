package easv.dk.eventticketsystem.bll;
import easv.dk.eventticketsystem.dal.db.OrderDAODB;

import java.sql.SQLException;


// Abstracts business logic like confirmOrder(), deleteOrder()
public class OrderManager {

    private final OrderDAODB orderDAO = new OrderDAODB();

    public int getNextOrderId() {
        return orderDAO.getNextOrderId();
    }
    public void updateOrderStatus(int orderId, String status) throws SQLException {
        orderDAO.updateOrderStatus(orderId, status);
    }


    public void deleteOrder(int orderId) throws SQLException {
        orderDAO.deleteOrder(orderId);
    }

    public void updateOrderCustomer(int orderId, int customerId) throws SQLException {
        orderDAO.updateCustomerId(orderId, customerId);
    }


}
