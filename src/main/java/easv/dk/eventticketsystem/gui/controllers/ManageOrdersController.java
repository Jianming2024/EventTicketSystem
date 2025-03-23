package easv.dk.eventticketsystem.gui.controllers;

import easv.dk.eventticketsystem.MainApplication;
import easv.dk.eventticketsystem.be.TicketOnOrder;
import easv.dk.eventticketsystem.bll.UUIDGenerator;
import easv.dk.eventticketsystem.gui.model.EventTicketSystemModel;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.image.ImageView;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class ManageOrdersController implements Initializable {
    @FXML
    private TableColumn colCustomerEmail;
    @FXML
    private TableColumn colCode;
    @FXML
    private TableView lstTicketOnOrder;
    @FXML
    private TableColumn colOrderId;
    @FXML
    private TableColumn colCustomerName;
    @FXML
    private TableColumn colEventName;
    @FXML
    private TableColumn colTicketQty;
    @FXML
    private TableColumn colTicketId;
    @FXML
    private TableColumn colTicketType;
    @FXML
    private ImageView qrCodeImageView;

    @FXML
    private Button printTicketButton;

    private final UUIDGenerator uuidGenerator = new UUIDGenerator();


    private final EventTicketSystemModel eventTicketSystemModel = new EventTicketSystemModel();
    private List<TicketOnOrder> ticketOnOrderList;

    public void initialize(URL location, ResourceBundle resources) {
        displayOrders();
    }

    public void displayOrders() {
        lstTicketOnOrder.setItems(eventTicketSystemModel.getAllOrderDetails());
        colOrderId.setCellValueFactory(new PropertyValueFactory<>("orderId"));
        colCustomerName.setCellValueFactory(new PropertyValueFactory<>("customerName"));
        colCustomerEmail.setCellValueFactory(new PropertyValueFactory<>("customerEmail"));
        colEventName.setCellValueFactory(new PropertyValueFactory<>("eventName"));
        colTicketId.setCellValueFactory(new PropertyValueFactory<>("ticketId"));
        colTicketType.setCellValueFactory(new PropertyValueFactory<>("ticketType"));
        colCode.setCellValueFactory(new PropertyValueFactory<>("code"));
    }

    public void onPrintTicketClick(MouseEvent event) {
        System.out.println("Button clicked");
        String ticket = "StandardTicket";

        // Generate QR Code and get the file path
        String qrFilePath = uuidGenerator.startQRCodeGeneration();

        if (qrFilePath != null) {
            System.out.println("QR Code generated at: " + qrFilePath);

            // Open StandardTicket window and pass the QR code
            setTicket(ticket, qrFilePath, event);
        } else {
            System.out.println("QR Code generation failed.");
        }


    }

    private void setTicket(String ticket,String qrFilePath, MouseEvent action) {
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
            ticketController.setQRCode(qrFilePath);


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


