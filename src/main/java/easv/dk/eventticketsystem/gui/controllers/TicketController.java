package easv.dk.eventticketsystem.gui.controllers;

import easv.dk.eventticketsystem.bll.UUIDGenerator;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.File;


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
    private ImageView qrCodeImageView; // ImageView in StandardTicket.fxml


    @FXML
    public void initialize() {
        // Initialization logic if needed
        System.out.println("TicketController initialized");
    }
    public void setQRCode(String qrFilePath) {
        if (qrFilePath != null) {
            File qrFile = new File(qrFilePath);
            if (qrFile.exists()) {
                Image qrImage = new Image(qrFile.toURI().toString());
                qrCodeImageView.setImage(qrImage);
                System.out.println("QR Code loaded into ImageView.");
            } else {
                System.out.println("QR Code image file not found.");
            }
        } else {
            System.out.println("QR Code file path is null.");
        }
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
