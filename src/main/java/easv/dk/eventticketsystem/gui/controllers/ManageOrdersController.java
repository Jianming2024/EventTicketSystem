package easv.dk.eventticketsystem.gui.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

public class ManageOrdersController {

    @FXML
    private TableView<Order> ordersTable;
    @FXML
    private TableColumn<Order, String> orderIdColumn;
    @FXML
    private TableColumn<Order, String> customerNameColumn;
    @FXML
    private TableColumn<Order, String> eventColumn;
    @FXML
    private TableColumn<Order, Integer> quantityColumn;
    @FXML
    private Button printTicketButton;

    public void onSearchOrder(ActionEvent actionEvent) {
    }

    // Internal Order class to store data for the TableView
    public static class Order {
        private String orderId;
        private String customerName;
        private String event;
        private int quantity;

        public Order(String orderId, String customerName, String event, int quantity) {
            this.orderId = orderId;
            this.customerName = customerName;
            this.event = event;
            this.quantity = quantity;
        }

        public String getOrderId() {
            return orderId;
        }

        public String getCustomerName() {
            return customerName;
        }

        public String getEvent() {
            return event;
        }

        public int getQuantity() {
            return quantity;
        }
    }

    // Initialize the TableView with the Order data
    @FXML
    public void initialize() {
        // Set up the columns in the TableView
        orderIdColumn.setCellValueFactory(new PropertyValueFactory<>("orderId"));
        customerNameColumn.setCellValueFactory(new PropertyValueFactory<>("customerName"));
        eventColumn.setCellValueFactory(new PropertyValueFactory<>("event"));
        quantityColumn.setCellValueFactory(new PropertyValueFactory<>("quantity"));

        // Add sample data to the TableView
        ordersTable.getItems().add(new Order("1", "John Doe", "Concert A", 2));
        ordersTable.getItems().add(new Order("2", "Jane Smith", "Event B", 3));
        ordersTable.getItems().add(new Order("3", "Alex Johnson", "Theater C", 1));
    }

    // Handle the Print Ticket button click
    @FXML
    public void onPrintTicketClick(MouseEvent event) {
        // Get the selected order from the TableView
        Order selectedOrder = ordersTable.getSelectionModel().getSelectedItem();
        if (selectedOrder != null) {
            // Print the ticket (Here, you can replace this with actual printing functionality)
            System.out.println("Printing ticket for Order ID: " + selectedOrder.getOrderId());
            System.out.println("Customer: " + selectedOrder.getCustomerName());
            System.out.println("Event: " + selectedOrder.getEvent());
            System.out.println("Quantity: " + selectedOrder.getQuantity());
        } else {
            // Alert the user if no order is selected
            System.out.println("Please select an order to print.");
        }
    }
}