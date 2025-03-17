package easv.dk.eventticketsystem.gui.controllers;

import easv.dk.eventticketsystem.be.TicketOnOrder;
import easv.dk.eventticketsystem.gui.models.EventTicketSystemModel;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

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

    }


}
