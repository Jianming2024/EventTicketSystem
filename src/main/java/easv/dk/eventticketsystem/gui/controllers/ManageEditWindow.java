package easv.dk.eventticketsystem.gui.controllers;

import easv.dk.eventticketsystem.MainApplication;
import easv.dk.eventticketsystem.be.Event;
import easv.dk.eventticketsystem.gui.controllers.componentsControllers.EditWindowController;
import easv.dk.eventticketsystem.gui.controllers.componentsControllers.EventCard2Controller;
import easv.dk.eventticketsystem.gui.model.EventTicketSystemModel;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class ManageEditWindow implements Initializable {
    @FXML
    private FlowPane eventCardPane;

    private static final EventTicketSystemModel model = new EventTicketSystemModel();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        if (eventCardPane == null) {
            System.out.println("DEBUG: eventCardPane is NULL in initialize()");
        } else {
            System.out.println("DEBUG: eventCardPane is initialized");
        }
    }
    private ManageEditWindow getManageEditWindowInstance() {
        // Find the currently loaded instance of ManageEditWindow
        FXMLLoader loader = new FXMLLoader(MainApplication.class.getResource("ManageEventView.fxml"));
        try {
            loader.load();  // Load the FXML file
        } catch (IOException e) {
            e.printStackTrace();
        }
        return loader.getController(); // Return the correct controller instance
    }
    public void loadAllEvents() throws IOException {
        if (eventCardPane == null) {
            System.out.println("ERROR: eventCardPane is NULL when trying to load events!");
            return;  // Prevents crashing
        }

        eventCardPane.getChildren().clear();
        List<Event> eventList = model.getAllEvents();
        for (Event event : eventList) {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/easv/dk/eventticketsystem/components/EventCard2.fxml"));
            AnchorPane card = loader.load();
            EventCard2Controller cardController = loader.getController();
            cardController.setEventData(event);
            eventCardPane.getChildren().add(card);
        }
    }
}
