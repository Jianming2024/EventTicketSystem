package easv.dk.eventticketsystem.gui.controllers.componentsControllers;

import easv.dk.eventticketsystem.be.TicketOnOrder;
import easv.dk.eventticketsystem.gui.model.EventTicketSystemModel;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class OrderCardController2 implements Initializable {
    @FXML
    private TableView <TicketOnOrder> ordersTable;
    @FXML
    private Label lblOrderNo;
    @FXML
    private Label lblCustomerName;
    @FXML
    private TableColumn colEventName;
    @FXML
    private TableColumn colEventType;
    @FXML
    private TableColumn colTicketQty;

    private static final EventTicketSystemModel model = new EventTicketSystemModel();
    private TicketOnOrder orderData;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void setOrderData(TicketOnOrder ticketOnOrder, List<TicketOnOrder> ticketList) {
        this.orderData = ticketOnOrder;
        loadOrderTable();
    }

    public void loadOrderTable() {
        ordersTable.getItems().clear();
        ordersTable.setItems(model.getAllOrderDetails());
        lblOrderNo.setText("Order Number: " + String.valueOf(orderData.getOrderId()));
        lblCustomerName.setText("Customer Name: " + orderData.getCustomerName());
        ordersTable.setItems(FXCollections.observableArrayList(orderData));
        colEventName.setCellValueFactory(new PropertyValueFactory<>("eventName"));
        colEventType.setCellValueFactory(new PropertyValueFactory<>("ticketType"));
    }

    public void onClickPrint(ActionEvent actionEvent) {
    }

    public void onClickEmail(ActionEvent actionEvent) {
    }

    public void onClickView(ActionEvent actionEvent) {
    }

    public void onClickEdit(ActionEvent actionEvent) {
    }

    public void onClickDelete(ActionEvent actionEvent) {
    }
}
