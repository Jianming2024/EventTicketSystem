package easv.dk.eventticketsystem.gui.controllers;

import easv.dk.eventticketsystem.be.TicketOnOrder;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.scene.control.Alert;
import javafx.stage.Stage;

import java.util.List;

public class OrderCardController {

    @FXML
    private Label lblOrderNumber;


    @FXML
    private HBox ticketContainer;
    @FXML
    private Button btnDelete;
    @FXML
    private Button btnAddTicket;
    @FXML
    private AnchorPane root;

    @FXML private TextField txtCustomerName;
    @FXML private TextField txtCustomerEmail;

    private TicketOnOrder ticketData;

    public void setData(TicketOnOrder baseTicket, List<TicketOnOrder> allTickets) {
        this.ticketData = baseTicket;
        lblOrderNumber.setText("Order #" + baseTicket.getOrderId());
        txtCustomerName.setText(baseTicket.getCustomerName());
        txtCustomerEmail.setText(baseTicket.getCustomerEmail());

        for (TicketOnOrder ticket : allTickets) {
            addTicketLabel("T" + ticket.getTicketId(), ticket);
        }
    }

    private void addTicketLabel(String ticketLabelText, TicketOnOrder ticket) {
        Label lbl = new Label(ticketLabelText);
        lbl.getStyleClass().add("ticket-box");

        lbl.setOnMouseClicked(event -> {
            System.out.println("Clicked " + ticketLabelText);
            handlePrintTicket(ticket); // ✅ actually using the correct ticket
        });

        ticketContainer.getChildren().add(lbl);
    }

    private void handlePrintTicket(TicketOnOrder ticket) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/easv/dk/eventticketsystem/StandardTicket.fxml"));
            Parent root = loader.load();

            TicketController ticketController = loader.getController();

            String qrPath = "qr_codes/" + ticket.getCode() + ".png";
            ticketController.setTicketData(ticket, qrPath);

            Stage stage = new Stage();
            stage.setTitle("Print Ticket");
            stage.setScene(new Scene(root));
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void setDataPlaceholder() {
        lblOrderNumber.setText("New Order");
        txtCustomerName.setPromptText("Customer name");
        txtCustomerEmail.setPromptText("Email address");

    }
    @FXML
    private void onDeleteClicked() {
        // Basic feedback for now
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Delete Order");
        alert.setHeaderText(null);
        alert.setContentText("Delete clicked for Order #" + ticketData.getOrderId());
        alert.showAndWait();

        // In the future: notify parent controller to remove this card from the list
    }

    @FXML
    private void onAddTicketClicked() {
        // Future: open modal to add new ticket to this order
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Add Ticket");
        alert.setHeaderText(null);
        alert.setContentText("Add Ticket clicked for Order #" + ticketData.getOrderId());
        alert.showAndWait();
    }
}
