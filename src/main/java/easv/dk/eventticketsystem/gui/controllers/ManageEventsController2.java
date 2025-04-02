package easv.dk.eventticketsystem.gui.controllers;

import easv.dk.eventticketsystem.MainApplication;
import easv.dk.eventticketsystem.be.Event;
import easv.dk.eventticketsystem.gui.controllers.componentsControllers.EventCard2Controller;
import easv.dk.eventticketsystem.gui.controllers.componentsControllers.EditWindowController;

import easv.dk.eventticketsystem.gui.model.EventTicketSystemModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class ManageEventsController2 implements Initializable {

    @FXML
    public Button btnCreateNewEvent;
    @FXML
    public FlowPane eventCardPane;

    @FXML
    private BorderPane eventPane;

    private static final EventTicketSystemModel model = new EventTicketSystemModel();


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            loadAllEvents();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void loadAllEvents() throws IOException {
        eventCardPane.getChildren().clear();
        List<Event> eventList = model.getAllEvents();
        for (Event event : eventList) {
            // Load the card component (EventCard.fxml) dynamically
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/easv/dk/eventticketsystem/components/EventCard2.fxml"));
            AnchorPane card = loader.load();
            // Get the controller of the card and pass the event data
            EventCard2Controller cardController = loader.getController();
            cardController.setParentController(this);
            cardController.setEventData(event);
            // Add the card to the FlowPane
            eventCardPane.getChildren().add(card);
            URL resource = getClass().getResource("/easv/dk/eventticketsystem/components/EventCard2.fxml");
            if (resource == null) {
                System.err.println("EventCard2.fxml resource not found!");
            }
        }
    }



//Opens window for create new event
    @FXML
    public void onClickAddEvent(ActionEvent actionEvent) throws IOException {

        FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("/easv/dk/eventticketsystem/CreateNewEventView.fxml"));


        Scene scene = new Scene(fxmlLoader.load());


        Stage loginStage = new Stage();


        loginStage.setTitle("Create A New Event");


        loginStage.setScene(scene);


        loginStage.show();

    }
}


