package easv.dk.eventticketsystem.gui.controllers.componentsControllers;

import easv.dk.eventticketsystem.be.TicketOnOrder;
import easv.dk.eventticketsystem.gui.controllers.TicketController;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.property.SimpleStringProperty;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.util.List;

public class OrderCardController {

    @FXML private Label lblOrderNumber;
    @FXML private Label lblCustomerName;

    @FXML private TableView<TicketOnOrder> ticketsTable;
    @FXML private TableColumn<TicketOnOrder, String> actionColumn;
    @FXML private TableColumn<TicketOnOrder, String> ticketTypeColumn;
    @FXML private TableColumn<TicketOnOrder, Integer> quantityColumn;

    @FXML private Button printOrderButton;
    @FXML private Button emailTicketsButton;
    @FXML private Button viewOrderButton;
    @FXML private Button editOrderButton;
    @FXML private Button deleteOrderButton;


    private List<TicketOnOrder> ticketList;
    private TicketOnOrder baseTicket;
    @FXML
    public void initialize() {

        ticketsTable.setRowFactory(tv -> {
            TableRow<TicketOnOrder> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (!row.isEmpty() && event.getClickCount() == 2) {
                    TicketOnOrder clickedTicket = row.getItem();
                    openTicket(clickedTicket);
                }
            });
            return row;
        });
    }

    public void setData(TicketOnOrder baseTicket, List<TicketOnOrder> allTickets) {
        this.baseTicket = baseTicket;
        this.ticketList = allTickets;

        lblOrderNumber.setText("Order #" + baseTicket.getOrderId());
        lblCustomerName.setText("Customer: "+ baseTicket.getCustomerName());

        ticketsTable.getItems().setAll(allTickets);
        configureTicketTableSizes();

        actionColumn.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getEventName()));
        ticketTypeColumn.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getTicketType()));
        quantityColumn.setCellValueFactory(data -> new ReadOnlyObjectWrapper<>(1));


    }
    //    //Method for Configuring the ticket table columns to automatically resize
    // * proportionally based on the table's total width.
    private void configureTicketTableSizes(){

        ticketsTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        ticketsTable.setFixedCellSize(30);
        ticketsTable.setPrefHeight(30 * 3 + 28); // 3 visible rows + header

        // Prevent extra invisible column from showing up
        ticketsTable.getColumns().forEach(col -> {
            col.setResizable(false);
            col.setReorderable(false);
        });

        ticketsTable.setPlaceholder(new Label("")); // prevents weird layout shift when empty

        actionColumn.prefWidthProperty().bind(ticketsTable.widthProperty().multiply(0.5));
        ticketTypeColumn.prefWidthProperty().bind(ticketsTable.widthProperty().multiply(0.3));
        quantityColumn.prefWidthProperty().bind(ticketsTable.widthProperty().multiply(0.2));

    }
    public void setDataPlaceholder() {
        lblOrderNumber.setText("New Order");
        lblCustomerName.setText("Customer: ");
        ticketsTable.getItems().clear();
    }

    private void openTicket(TicketOnOrder ticket) {
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

    @FXML
    private void onDeleteClicked() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Delete Order");
        alert.setHeaderText(null);
        alert.setContentText("Delete clicked for Order #" + baseTicket.getOrderId());
        alert.showAndWait();
    }

    @FXML
    private void onAddTicketClicked() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Add Ticket");
        alert.setHeaderText(null);
        alert.setContentText("Add Ticket clicked for Order #" + baseTicket.getOrderId());
        alert.showAndWait();
    }

    @FXML
    private void onPrintOrderClicked() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/easv/dk/eventticketsystem/StandardTicket.fxml"));
            Parent root = loader.load();
            TicketController ticketController = loader.getController();
            String qrPath = "qr_codes/" + baseTicket.getCode() + ".png";
            ticketController.setTicketData(baseTicket, qrPath);

            Stage stage = new Stage();
            stage.setTitle("Print Ticket");
            stage.setScene(new Scene(root));
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}