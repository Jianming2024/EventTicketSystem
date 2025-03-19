package easv.dk.eventticketsystem.gui.controllers;

import easv.dk.eventticketsystem.MainApplication;
import easv.dk.eventticketsystem.be.TicketOnOrder;
import easv.dk.eventticketsystem.gui.models.EventTicketSystemModel;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
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
    private Button printTicketButton;

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
        setTicket(ticket, event);
    }
    private void setTicket(String ticket, MouseEvent action) {
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

//            // Ensure the controller is correctly assigned
//            ManageTicketsController tck = fxmlLoader.getController();
//            if (tck != null) {
//                tck.setParentController(this);
//            } else {
//                System.err.println("⚠️ Controller is null! Check FXML `fx:controller` declaration.");
//            }

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

    private void setTicket17(String ticket, MouseEvent action) {
        try {
            // Correct path to the FXML file
            URL location = getClass().getClassLoader().getResource("StandardTicket.fxml");
            if (location == null) {
                System.err.println("FXML file not found at StandardTicket.fxml");
                return;
            }
            System.out.println("Loading FXML from: " + location);

            FXMLLoader fxmlLoader = new FXMLLoader(location);
            Scene scene = new Scene(fxmlLoader.load());
            ManageTicketsController tck = fxmlLoader.getController();
            tck.setParentController(this);
            Stage stage = new Stage();
            stage.setTitle("Event Ticket System");
            stage.setScene(scene);
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
            // Show an alert to the user
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Failed to load ticket view");
            alert.setContentText(e.getMessage());
            alert.showAndWait();
        }
    }


    private void setTicket5(String ticket, MouseEvent action) {
        try {
            // Load the FXML file from the correct path
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("StandardTicket.fxml"));
            Parent root = fxmlLoader.load();

            // If you need access to the controller
            ManageTicketsController tck = fxmlLoader.getController();
            tck.setParentController(this);

            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setTitle("Standard Ticket");
            stage.setScene(scene);
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Failed to load ticket view");
            alert.setContentText(e.getMessage());
            alert.showAndWait();
        }
    }
    private void setTicket3(String ticket, MouseEvent action) {
        try {
            // Load the FXML file using MainApplication class reference
            FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("StandardTicket.fxml"));
            Parent root = fxmlLoader.load();

            // Get controller to set the parent controller
            ManageTicketsController tck = fxmlLoader.getController();
            tck.setParentController(this);

            // Create a new stage
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setTitle("Standard Ticket");
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            System.out.println("Error loading FXML: " + e.getMessage());
            showErrorAlert("Failed to load ticket view", e.getMessage());
            // Print the resource path being used
            System.out.println("Attempting to load: " + MainApplication.class.getResource("StandardTicket.fxml"));
        }
    }
    private void showErrorAlert(String header, String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(header);
        alert.setContentText(message);
        alert.showAndWait();
    }
}


/*
    private void setTicket(String ticket, MouseEvent action) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("StandardTicket.fxml"));
            Scene scene = new Scene(fxmlLoader.load());
            ManageTicketsController tck = fxmlLoader.getController();
            tck.setParentController(this);
            Stage stage = new Stage();
            stage.setTitle("Event Ticket System");
            stage.setScene(scene);
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        } System.out.println(MainApplication.class.getResource("/StandardTicket.fxml"));
    }
}*/


   /* public void setTicket(Stage stage, MouseEvent event) throws IOException {

        Parent root = FXMLLoader.load(getClass().getResource("StandardTicket.fxml"));
        Scene scene = new Scene(root);
        scene.getStylesheets().add(BootstrapFX.bootstrapFXStylesheet());
        stage.setTitle("Standard Ticket");
        stage.setScene(scene);
        stage.show();
    }
}*/