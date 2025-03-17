package easv.dk.eventticketsystem.gui.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

import java.net.URL;
import java.util.ResourceBundle;

public class ManageOrdersController implements Initializable {
    @FXML
    private TableColumn colCustomerEmail;
    @FXML
    private TableColumn colCode;
    @FXML
    private TableView ordersTable;
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

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        colOrderId.setCellValueFactory(new PropertyValueFactory<>("orderId"));
        colCustomerName.setCellValueFactory(new PropertyValueFactory<>("customerName"));
        colEventName.setCellValueFactory(new PropertyValueFactory<>("eventName"));
        colCustomerEmail.setCellValueFactory(new PropertyValueFactory<>("customerEmail"));
        colEventName.setCellValueFactory(new PropertyValueFactory<>("eventName"));
        colTicketId.setCellValueFactory(new PropertyValueFactory<>("ticketId"));
        colTicketType.setCellValueFactory(new PropertyValueFactory<>("ticketType"));
        colCode.setCellValueFactory(new PropertyValueFactory<>("code"));
    }

    public void onPrintTicketClick(MouseEvent event) {

    }


}
