package easv.dk.eventticketsystem.dal;

import easv.dk.eventticketsystem.be.Orders;

import java.util.List;

public interface IOrderDAO {
    List<Orders> getAllOrders();
}
