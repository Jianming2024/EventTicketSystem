package easv.dk.eventticketsystem.gui.controllers.componentsControllers;

import easv.dk.eventticketsystem.be.Events;
import easv.dk.eventticketsystem.gui.model.EventTicketSystemModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;

import javax.swing.text.html.ImageView;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;


public class EventCard3Controller {
    @FXML
    private Label lblEventName;
    @FXML
    private Label lblStartTime;
    @FXML
    private Label lblEndTime;
    @FXML
    private Label lblLocation;
    @FXML
    private Label lblExpand;
    @FXML
    private ImageView eventImage;
    @FXML
    private FlowPane eventCardPane;


    public void setEventData(Events event) {
        lblEventName.setText(event.getEventName());
        lblStartTime.setText(event.getStartDatetime().toString());
        lblEndTime.setText(event.getEndDatetime().toString());
        lblLocation.setText(event.getLocation());
        lblExpand.setText(event.getNotes());
    }


    public void onEditClick(ActionEvent actionEvent) {
    }

    public void onDeleteClick(ActionEvent actionEvent) {
    }
}
