package easv.dk.eventticketsystem.gui.controllers;

import easv.dk.eventticketsystem.MainApplication;
import easv.dk.eventticketsystem.be.TicketOnOrder;
import easv.dk.eventticketsystem.bll.TicketManager;
import easv.dk.eventticketsystem.bll.UUIDGenerator;
import easv.dk.eventticketsystem.gui.controllers.componentsControllers.OrderCardController;
import easv.dk.eventticketsystem.gui.model.EventTicketSystemModel;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.*;

public class ManageOrdersController implements Initializable {

    public BorderPane ordersPane;
    public ScrollPane scrollPane;
    public Button btnConfirmOrder;
    public Button btnDeleteOrder;
    @FXML
    private FlowPane orderCardContainer;
    @FXML
    private TableColumn<TicketOnOrder, String> colCustomerEmail;
    @FXML
    private TableColumn<TicketOnOrder, Integer> colCode;

    @FXML
    private TableView<TicketOnOrder> lstTicketOnOrder;

    @FXML
    private TableColumn<TicketOnOrder, Integer> colOrderId;

    @FXML
    private TableColumn<TicketOnOrder, String> colCustomerName;

    @FXML
    private TableColumn<TicketOnOrder, String> colEventName;
//    @FXML
//    private TableColumn colTicketQty;

    @FXML
    private TableColumn<TicketOnOrder, Integer>colTicketId;
    @FXML
    private TableColumn<TicketOnOrder, String>colTicketType;
    private TicketOnOrder selectedOrder;

    private Parent selectedCardNode;


    @FXML
    public Button btnCreateNewOrder;


    private final UUIDGenerator uuidGenerator = new UUIDGenerator();


    private final EventTicketSystemModel eventTicketSystemModel = new EventTicketSystemModel();
    private List<TicketOnOrder> ticketOnOrder;

    public void initialize(URL location, ResourceBundle resources) {


        scrollPane.widthProperty().addListener((obs, oldVal, newVal) -> {
            orderCardContainer.setPrefWidth(newVal.doubleValue() - 20);
        });
        displayOrders();
    }

    public void displayOrders() {
        orderCardContainer.getChildren().clear();
        // Dynamically adjust wrap length to match current width
        orderCardContainer.setPrefWrapLength(orderCardContainer.getWidth());
        orderCardContainer.widthProperty().addListener((obs, oldVal, newVal) -> {
            orderCardContainer.setPrefWrapLength(newVal.doubleValue());
        });

        List<TicketOnOrder> tickets = eventTicketSystemModel.getAllOrderDetails();
/// Groups tickets with Order
        Map<Integer, List<TicketOnOrder>> groupedOrders = new HashMap<>();
        for (TicketOnOrder ticket : tickets) {
            int orderId = ticket.getOrderId();
            groupedOrders.computeIfAbsent(orderId, k -> new ArrayList<>()).add(ticket);
        }

        for (Map.Entry<Integer, List<TicketOnOrder>> entry : groupedOrders.entrySet()) {
            List<TicketOnOrder> ticketList = entry.getValue();
            for (TicketOnOrder t : ticketList) {
                System.out.println("🔍 Email for ticket in order " + t.getOrderId() + ": " + t.getCustomerEmail());
            }
            TicketOnOrder baseTicket = ticketList.get(0); // Use first as base for name/email
            System.out.println("📦 Creating card for Order #" + baseTicket.getOrderId() + ", total tickets: " + ticketList.size());

            try {
                URL fxmlPath = getClass().getResource("/easv/dk/eventticketsystem/components/OrderCard.fxml");
                System.out.println("📄 Loading OrderCard.fxml from: " + fxmlPath);

                FXMLLoader loader = new FXMLLoader(fxmlPath);
                Parent card = loader.load();

                OrderCardController controller = loader.getController();
                System.out.println("👀 Loaded controller: " + controller);
                controller.setParentController(this);
                controller.setModel(eventTicketSystemModel);

                controller.setData(baseTicket, ticketList);
                System.out.println("✅ Finished setData() for Order #" + baseTicket.getOrderId());

                orderCardContainer.getChildren().add(card);

            } catch (IOException e) {
                System.err.println("❌ Error loading OrderCard.fxml for Order #" + baseTicket.getOrderId());
                e.printStackTrace();
            }
        }
    }

    public void setSelectedOrder(TicketOnOrder order, Parent cardNode) {
        if (selectedCardNode != null) {
            selectedCardNode.getStyleClass().remove("order-card-selected");
            selectedCardNode.getStyleClass().add("order-card"); // return it to base style
        }

        selectedCardNode = cardNode;
        selectedCardNode.getStyleClass().remove("order-card"); // remove base style
        selectedCardNode.getStyleClass().add("order-card-selected"); // add selected

        this.selectedOrder = order;
        System.out.println("📌 Selected order #" + order.getOrderId());
    }

    @FXML
    private void onClickAddOrder() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/easv/dk/eventticketsystem/components/OrderCard.fxml"));
            Parent card = loader.load();

            OrderCardController controller = loader.getController();
            controller.setDataPlaceholder(); // Shows placeholder text like "New Order"

            orderCardContainer.getChildren().add(card);

            int newOrderId = eventTicketSystemModel.getNextOrderId();
            TicketOnOrder placeholderTicket = new TicketOnOrder(
                    newOrderId, "Customer Name", "email@example.com", "Event Placeholder",
                    0, "Type", "CODE123", "DD/MM/YYYY", "HH:mm", "Location", "0", 1
            );
            controller.setData(placeholderTicket, List.of(placeholderTicket));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @FXML
    private void onClickConfirmOrder() {
        if (selectedOrder == null) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("No Order Selected");
            alert.setHeaderText(null);
            alert.setContentText("Please select an order to confirm.");
            alert.showAndWait();
            return;
        }

        // Update status in DB
        eventTicketSystemModel.confirmOrder(selectedOrder.getOrderId());

        // Refresh UI
        displayOrders();

        // Reset selected order
        selectedOrder = null;
        selectedCardNode = null;

        // Confirmation message
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Order Confirmed");
        alert.setHeaderText(null);
        alert.setContentText("Order has been confirmed and moved to history.");
        alert.showAndWait();
    }


    @FXML
    private void onClickDeleteOrder() {

        if (selectedOrder == null) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("No Order Selected");
            alert.setHeaderText(null);
            alert.setContentText("Please select an order to delete.");
            alert.showAndWait();
            return;
        }

        Alert confirmAlert = new Alert(Alert.AlertType.CONFIRMATION);
        confirmAlert.setTitle("Confirm Deletion");
        confirmAlert.setHeaderText("Are you sure you want to delete this order?");
        confirmAlert.setContentText("Order ID: " + selectedOrder.getOrderId());

        ///  TODO maybe change this Lambda
        confirmAlert.showAndWait().ifPresent(result -> {
            if (result == ButtonType.OK) {
                try {
                    eventTicketSystemModel.deleteOrder(selectedOrder.getOrderId());
                    orderCardContainer.getChildren().remove(selectedCardNode);

                    System.out.println("🗑️ Deleted order ID: " + selectedOrder.getOrderId());

                    selectedOrder = null;
                    selectedCardNode = null;

                } catch (Exception e) {
                    e.printStackTrace();
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error");
                    alert.setHeaderText("Failed to delete order.");
                    alert.setContentText(e.getMessage());
                    alert.showAndWait();
                }
            }
        });

    }


}




