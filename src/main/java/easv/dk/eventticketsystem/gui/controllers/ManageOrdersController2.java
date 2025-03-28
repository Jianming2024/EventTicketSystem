package easv.dk.eventticketsystem.gui.controllers;

import easv.dk.eventticketsystem.be.TicketOnOrder;
import easv.dk.eventticketsystem.be.Users;
import easv.dk.eventticketsystem.gui.controllers.componentsControllers.OrderCardController2;
import easv.dk.eventticketsystem.gui.controllers.componentsControllers.UserCardController;
import easv.dk.eventticketsystem.gui.model.EventTicketSystemModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;

import java.io.IOException;
import java.net.URL;
import java.util.*;

public class ManageOrdersController2 implements Initializable {
    @FXML
    private FlowPane orderCardPane;
    @FXML
    private BorderPane ordersPane;

    private static final EventTicketSystemModel model = new EventTicketSystemModel();
    private List<TicketOnOrder> TicketOnOrderList;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            loadAllOrders();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void loadAllOrders() throws IOException {
        orderCardPane.getChildren().clear();
        List<TicketOnOrder> ticketOnOrderList = model.getAllOrderDetails();
        // Group orders by orderId
        Map<Integer, List<TicketOnOrder>> groupedOrders = new HashMap<>();
        for (TicketOnOrder ticketOnOrder : ticketOnOrderList) {
            int orderId = ticketOnOrder.getOrderId();
            groupedOrders.computeIfAbsent(orderId, k -> new ArrayList<>()).add(ticketOnOrder); // <-- Added semicolon
        }

        // For each grouped order, load an order card
        for (Map.Entry<Integer, List<TicketOnOrder>> entry : groupedOrders.entrySet()) {
            List<TicketOnOrder> ticketList = entry.getValue();
            // Use the first TicketOnOrder as the "base" for general info
            TicketOnOrder baseTicket = ticketList.get(0);

            try {
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/easv/dk/eventticketsystem/components/OrderCard2.fxml"));
                AnchorPane card = fxmlLoader.load();
                OrderCardController2 cardController = fxmlLoader.getController();
                // Set the order data; note: you need a method that accepts both base ticket and ticket list
                cardController.setOrderData(baseTicket, ticketList);
                orderCardPane.getChildren().add(card);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void onClickAddOrder(ActionEvent actionEvent) {

    }
}
