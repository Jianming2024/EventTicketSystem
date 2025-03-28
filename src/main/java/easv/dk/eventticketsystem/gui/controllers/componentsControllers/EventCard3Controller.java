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


public class EventCard3Controller implements Initializable {
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            loadAllEvents();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

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

    private static final EventTicketSystemModel model = new EventTicketSystemModel();

    public void setEventData(Events event) {
        lblEventName.setText(event.getEventName());
        lblStartTime.setText(event.getStartDatetime().toString());
        lblEndTime.setText(event.getEndDatetime().toString());
        lblLocation.setText(event.getLocation());
        lblExpand.setText(event.getNotes());
    }

    private void loadAllEvents() throws IOException {
        eventCardPane.getChildren().clear();
        List<Events> EventsList = model.getAllEvents();
        for (Events event : EventsList) {
            // Load the card component (EventCard3.fxml) dynamically
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/easv/dk/eventticketsystem/components/EventCard3.fxml"));
            AnchorPane card = loader.load();
            // Get the controller of the card and pass the event data
            EventCardController cardController = loader.getController();
            cardController.setEventData(event);
            // Add the card to the FlowPane
            eventCardPane.getChildren().add(card);
        }
    }



    public void onEditClick(ActionEvent actionEvent) {
    }

    public void onDeleteClick(ActionEvent actionEvent) {
    }
}
