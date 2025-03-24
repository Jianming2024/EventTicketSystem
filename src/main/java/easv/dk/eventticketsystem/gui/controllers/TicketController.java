package easv.dk.eventticketsystem.gui.controllers;

import easv.dk.eventticketsystem.be.TicketOnOrder;
import easv.dk.eventticketsystem.bll.UUIDGenerator;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.File;


public class TicketController {


    @FXML private Label lblEventName;
    @FXML private Label lblCustomerName;
    @FXML private Label lblDate;
    @FXML private Label lblTime;
    @FXML private Label lblPrice;
    @FXML private Label lblLocation;
    @FXML private ImageView qrCodeImageView;



    @FXML
    public void initialize() {

        System.out.println("Super duper cool ticket displaying!");
    }
    public void setTicketData(TicketOnOrder ticket, String qrFilePath) {

        lblEventName.setText(ticket.getEventName());
//        lblCustomerName.setText(ticket.getCustomerName());  Uncomment if adding the customer name is needed

        lblDate.setText(ticket.getEventDate());
        lblTime.setText(ticket.getEventTime());
        lblPrice.setText(ticket.getPrice());
        lblLocation.setText(ticket.getLocation());


        // Set QR image
        File qrFile = new File(qrFilePath);
        if (qrFile.exists()) {
            Image qrImage = new Image(qrFile.toURI().toString());
            qrCodeImageView.setImage(qrImage);
        } else {
            System.out.println("QR code image not found at: " + qrFilePath);
        }
    }

}
