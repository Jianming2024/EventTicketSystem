package easv.dk.eventticketsystem.gui.controllers;

import easv.dk.eventticketsystem.be.Events;
import easv.dk.eventticketsystem.gui.controllers.componentsControllers.EventCardController;
import easv.dk.eventticketsystem.gui.model.EventTicketSystemModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class ManageEventsController2 implements Initializable {

        @FXML
        public Button btnCreateNewEvent;
        @FXML
        private FlowPane eventCardPane;
        @FXML
        private BorderPane eventPane;

        private static final EventTicketSystemModel model = new EventTicketSystemModel();
        private List<Events> eventsList;

        @Override
        public void initialize(URL location, ResourceBundle resources) {
            try {
                loadAllEvents();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        private void loadAllEvents() throws IOException {
            eventCardPane.getChildren().clear();
            List<Events> eventsList = model.getAllEvents();
            for (Events event : eventsList) {
                // Load the card component (EventCard.fxml) dynamically
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/easv/dk/eventticketsystem/components/EventCard3.fxml"));
                AnchorPane card = loader.load();
                // Get the controller of the card and pass the event data
                EventCardController cardController = loader.getController();
                cardController.setEventData(event);
                // Add the card to the FlowPane
                eventCardPane.getChildren().add(card);
            }
        }

        private void onEditClicked(Events event) {
            System.out.println("Edit clicked for event: " + event);

        }

        private void onDeleteClicked(Events event) {
            System.out.println("Delete clicked for event: " + event);

        }

        public void onClickAddEvent(ActionEvent actionEvent) {
        }
    }
