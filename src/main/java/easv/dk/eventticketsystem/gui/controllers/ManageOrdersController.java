package easv.dk.eventticketsystem.gui.controllers;

import easv.dk.eventticketsystem.MainApplication;
import easv.dk.eventticketsystem.be.TicketOnOrder;
import easv.dk.eventticketsystem.bll.UUIDGenerator;
import easv.dk.eventticketsystem.gui.controllers.componentsControllers.OrderCardController;
import easv.dk.eventticketsystem.gui.model.EventTicketSystemModel;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
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


    @FXML
    private Button btnCreateNewOrder;


    private final UUIDGenerator uuidGenerator = new UUIDGenerator();


    private final EventTicketSystemModel eventTicketSystemModel = new EventTicketSystemModel();
    private List<TicketOnOrder> ticketOnOrderList;

    public void initialize(URL location, ResourceBundle resources) {
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

        Map<Integer, List<TicketOnOrder>> groupedOrders = new HashMap<>();
        for (TicketOnOrder ticket : tickets) {
            int orderId = ticket.getOrderId();
            groupedOrders.computeIfAbsent(orderId, k -> new ArrayList<>()).add(ticket);
        }

        for (Map.Entry<Integer, List<TicketOnOrder>> entry : groupedOrders.entrySet()) {
            List<TicketOnOrder> ticketList = entry.getValue();
            TicketOnOrder baseTicket = ticketList.get(0); // Use first as base for name/email
            System.out.println("📦 Creating card for Order #" + baseTicket.getOrderId() + ", total tickets: " + ticketList.size());

            try {
                URL fxmlPath = getClass().getResource("/easv/dk/eventticketsystem/components/OrderCard.fxml");
                System.out.println("📄 Loading OrderCard.fxml from: " + fxmlPath);

                FXMLLoader loader = new FXMLLoader(fxmlPath);
                Parent card = loader.load();

                OrderCardController controller = loader.getController();
                System.out.println("👀 Loaded controller: " + controller);

                controller.setData(baseTicket, ticketList);
                System.out.println("✅ Finished setData() for Order #" + baseTicket.getOrderId());

                orderCardContainer.getChildren().add(card);

            } catch (IOException e) {
                System.err.println("❌ Error loading OrderCard.fxml for Order #" + baseTicket.getOrderId());
                e.printStackTrace();
            }
        }
    }
    @FXML
    private void onClickAddOrder() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/easv/dk/eventticketsystem/components/OrderCard.fxml"));
            Parent card = loader.load();

            OrderCardController controller = loader.getController();
            controller.setDataPlaceholder(); // Shows placeholder text like "New Order"

            orderCardContainer.getChildren().add(card);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }




    public void onPrintTicketClick(MouseEvent event) {
        System.out.println("Button clicked");

        TicketOnOrder selected = lstTicketOnOrder.getSelectionModel().getSelectedItem();
        if (selected == null) return;

        String code = selected.getCode(); // the uniqueCode
        String qrFilePath = System.getProperty("user.dir") + "/qr_codes/" + code + ".png";

        setTicket(selected, qrFilePath, event);


    }

    private void setTicket(TicketOnOrder ticket, String qrFilePath, MouseEvent action) {
        try {
            // Load the FXML file using the MainApplication class reference
            FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("StandardTicket.fxml"));

            if (fxmlLoader.getLocation() == null) {
                System.err.println("❌ FXML file not found! Java is looking in: " + MainApplication.class.getResource("/"));
                return;
            } else {
                System.out.println("✅ FXML file found at: " + fxmlLoader.getLocation());
            }
            Parent root = fxmlLoader.load();
            // Get the TicketController
            TicketController ticketController = fxmlLoader.getController();
            // Pass the QR code image path to the TicketController
            ticketController.setTicketData(ticket, qrFilePath);


            Stage stage = new Stage();
            stage.setTitle("Event Ticket System");
            stage.setScene(new Scene(root));
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();

            // Print the exact cause of the error
            System.err.println("❌ ERROR MESSAGE: " + e.getMessage());

            // Show an alert to the user
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Failed to load ticket view");
            alert.setContentText("Full Error: " + e.toString());
            alert.showAndWait();
        }
    }
}




