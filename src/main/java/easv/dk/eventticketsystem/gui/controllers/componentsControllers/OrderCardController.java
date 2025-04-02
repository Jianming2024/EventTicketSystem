package easv.dk.eventticketsystem.gui.controllers.componentsControllers;

import com.itextpdf.text.Document;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.pdf.PdfWriter;
import easv.dk.eventticketsystem.be.TicketOnOrder;
import easv.dk.eventticketsystem.bll.TicketManager;
import easv.dk.eventticketsystem.gui.controllers.ManageOrdersController;
import easv.dk.eventticketsystem.gui.controllers.TicketController;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.embed.swing.SwingFXUtils;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.util.List;

public class OrderCardController {

    @FXML private Button deleteTicketButton;
    @FXML private Button addTicketButton;
    @FXML private VBox cardRoot;
    @FXML
    private AnchorPane containerRoot;
    @FXML private Label lblOrderNumber;
    @FXML private Label lblCustomerName;

    @FXML private TableView<TicketOnOrder> ticketsTable;
    @FXML private TableColumn<TicketOnOrder, String> actionColumn;
    @FXML private TableColumn<TicketOnOrder, String> ticketTypeColumn;
    @FXML private TableColumn<TicketOnOrder, Integer> quantityColumn;

    @FXML private Button printOrderButton;
    @FXML private Button emailTicketsButton;

    @FXML private Button editOrderButton;
    @FXML private Button deleteOrderButton;
    private ManageOrdersController parentController;

    private List<TicketOnOrder> ticketList;
    private TicketOnOrder baseTicket;
    @FXML
    public void initialize() {
        containerRoot.getStyleClass().add("order-card");
        System.out.println("✅ OrderCardController initialized: " + this);

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
        System.out.println("✅ setData called on OrderCardController - Order #" + baseTicket.getOrderId());
        this.baseTicket = baseTicket;
        this.ticketList = allTickets;
        System.out.println("Tickets for order #" + baseTicket.getOrderId() + ": " + allTickets.size());

        lblOrderNumber.setText("Order #" + baseTicket.getOrderId());
        lblCustomerName.setText("Customer: "+ baseTicket.getCustomerName());

        ticketsTable.getItems().setAll(allTickets);
        configureTicketTableSizes();

        actionColumn.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getEventName()));
        ticketTypeColumn.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getTicketType()));
        quantityColumn.setCellValueFactory(data -> new SimpleIntegerProperty(data.getValue().getQuantity()).asObject());

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
        ticketsTable.getItems().forEach(ticket -> {
            System.out.println("Ticket for Order #" + ticket.getOrderId() + " - " + ticket.getEventName());
        });

    }
    public void setDataPlaceholder() {
        lblOrderNumber.setText("New Order");
        lblCustomerName.setText("Customer: ");
        ticketsTable.getItems().clear();
        configureTicketTableSizes();
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
    public void setParentController(ManageOrdersController controller) {
        this.parentController = controller;
    }

    @FXML
    private void onCardClicked() {
        if (parentController != null && baseTicket != null) {
            parentController.setSelectedOrder(baseTicket, containerRoot);
        }
    }

    @FXML
    private void onDeleteClicked() {
        TicketOnOrder selectedTicket = ticketsTable.getSelectionModel().getSelectedItem();

        if (selectedTicket == null) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("No Ticket Selected");
            alert.setHeaderText(null);
            alert.setContentText("Please select a ticket to delete.");
            alert.showAndWait();
            return;
        }
        Alert confirmAlert = new Alert(Alert.AlertType.CONFIRMATION);
        confirmAlert.setTitle("Confirm Deletion");
        confirmAlert.setHeaderText("Are you sure you want to delete this ticket?");
        confirmAlert.setContentText("Ticket: " + selectedTicket.getTicketType());

        confirmAlert.showAndWait().ifPresent(result -> {
            if (result == ButtonType.OK) {
                try {

                    // Should probably change the Ticket manager to model - lucas
                    TicketManager ticketManager = new TicketManager();
                    ticketManager.deleteTicket(selectedTicket.getCode()); // ✅ delete from DB

                    ticketsTable.getItems().remove(selectedTicket); // ✅ remove from UI
                    System.out.println("🗑️ Deleted ticket with code: " + selectedTicket.getCode());

                } catch (Exception e) {
                    e.printStackTrace();
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error");
                    alert.setHeaderText("Failed to delete ticket.");
                    alert.setContentText(e.getMessage());
                    alert.showAndWait();
                }
            }
        });
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

        //Chooses where to save the PDF = "Save as" function
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Saved all tickets to PDF from Order" + baseTicket.getOrderId());
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("PDF Files", "*.pdf"));

        File file = fileChooser.showSaveDialog(null);

        System.out.println("✅ Ticket PDF generated!");

        if (file == null) return;

        try {
            Document document = new Document(PageSize.A4.rotate());
            PdfWriter.getInstance(document, new FileOutputStream(file));
            document.open();

            for (TicketOnOrder ticket : ticketList) {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/easv/dk/eventticketsystem/StandardTicket.fxml"));
                Parent root = loader.load();

                TicketController controller = loader.getController();
                String qrPath = "qr_codes/" + ticket.getCode() + ".png";
                controller.setTicketData(ticket, qrPath);

                // Render the node (scene snapshot)
                Scene tempScene = new Scene(root);
                WritableImage snapshot = tempScene.snapshot(null);
                BufferedImage bufferedImage = SwingFXUtils.fromFXImage(snapshot, null);

                File tempImage = File.createTempFile("ticket", ".png");
                ImageIO.write(bufferedImage, "png", tempImage);

                com.itextpdf.text.Image pdfImg = com.itextpdf.text.Image.getInstance(tempImage.getAbsolutePath());
                pdfImg.scaleToFit(800, 600);
                float x = (PageSize.A4.getHeight() - pdfImg.getScaledWidth()) / 2;
                float y = (PageSize.A4.getWidth() - pdfImg.getScaledHeight()) / 2;
                pdfImg.setAbsolutePosition(x, y);
                document.newPage();
                document.add(pdfImg);

                tempImage.delete(); // optional cleanup
            }

            document.close();
            System.out.println("✅ All tickets exported to PDF: " + file.getAbsolutePath());
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("❌ Failed to export all tickets: " + e.getMessage());
        }

    }
}