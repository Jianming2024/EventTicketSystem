package easv.dk.eventticketsystem.gui.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class TicketController {
    @FXML
    private TextField eventTitleField;

    @FXML
    private TextField nameField;

    @FXML
    private TextField locationField;

    @FXML
    private TextField dateField;

    @FXML
    private TextField timeField;

    @FXML
    private TextField priceField;

    @FXML
    public void initialize() {
        // Initialization logic if needed
        System.out.println("TicketController initialized");
    }

    public String getEventTitle() {
        return eventTitleField.getText();
    }

    public String getName() {
        return nameField.getText();
    }

    public String getLocation() {
        return locationField.getText();
    }

    public String getDate() {
        return dateField.getText();
    }

    public String getTime() {
        return timeField.getText();
    }

    public String getPrice() {
        return priceField.getText();
    }
}
