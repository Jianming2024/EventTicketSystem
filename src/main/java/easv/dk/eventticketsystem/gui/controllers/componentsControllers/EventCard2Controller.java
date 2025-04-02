package easv.dk.eventticketsystem.gui.controllers.componentsControllers;

import easv.dk.eventticketsystem.MainApplication;
import easv.dk.eventticketsystem.be.Event;
import easv.dk.eventticketsystem.gui.controllers.ManageEditWindow;
import easv.dk.eventticketsystem.gui.controllers.ManageEventsController2;
import easv.dk.eventticketsystem.gui.controllers.ManageUsersController;
import easv.dk.eventticketsystem.gui.model.EventTicketSystemModel;
import easv.dk.eventticketsystem.gui.util.AlertUtil;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;

import java.io.IOException;

public class EventCard2Controller {
    private Event currentEvent;
    public void setEvent(Event event) {
        this.currentEvent = event;
    }

    @FXML
    private ImageView eventImage;
    @FXML
    private Label lblEventName;
    @FXML
    private Label lblLocation;
    @FXML
    private Label lblStartTime;
    @FXML
    private Label lblEndTime;

    private Event event;
    private final EventTicketSystemModel model = new EventTicketSystemModel();
    private ManageEventsController2  manageEventsController;


    public void setEventData(Event event) {
        this.event = event;
        if (event == null) {
            System.out.println("DEBUG: setEventData called with null event");
            return;
        }

        this.currentEvent = event; // Store the event properly

        lblEventName.setText(event.getEventName());
        lblLocation.setText(event.getLocation());
        lblStartTime.setText(event.getStartDatetime().toString());
        lblEndTime.setText(event.getEndDatetime().toString());

//        String imagePath = event.getEventImagePath();
//        if (imagePath != null && !imagePath.isEmpty()) {
//            Image image = new Image(getClass().getResourceAsStream(imagePath));
//            eventImage.setImage(image);
//        }

        System.out.println("DEBUG: Event stored successfully -> " + currentEvent.getEventName());
    }

    public void onClickEdit(ActionEvent actionEvent) {
        loadEditWindow();
    }

    private void loadEditWindow() {
        if (currentEvent == null) {
            AlertUtil.showErrorAlert("Error", "No event selected.");
            System.out.println("DEBUG: No event selected in loadEditWindow()");
            return;
        }

        try {
            FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("EditEventView.fxml"));
            Parent root = fxmlLoader.load();

            // Get the edit window controller
            EditWindowController ew = fxmlLoader.getController();
            ew.setEvent(currentEvent);  // Pass selected event
            ew.setParentController(getManageEditWindow()); // Pass parent controller
            ew.loadEventData(currentEvent);

            Stage stage = new Stage();
            stage.setTitle("Edit Event");
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
            AlertUtil.showErrorAlert("Error", "Failed to load the edit window.");
        }
    }

    private ManageEditWindow getManageEditWindow() {
        return new ManageEditWindow();
    }


    public void onClickDelete(ActionEvent actionEvent)throws IOException {
        boolean confirmed = AlertUtil.showConfirmationAlert("Delete Event Confirmation",
                "Are you sure you want to delete this event?");
        if (confirmed) {
            model.deleteEvent(event);

        } else {
            System.out.println("Event deletion canceled");
        }
        // Refresh the list of users using the parent controller, if available
        if (manageEventsController != null) {
            manageEventsController.loadAllEvents();
        } else {
            System.err.println("Parent controller is not set!");
        }
    }

    public void setParentController(ManageEventsController2 manageEventsController) {
        this.manageEventsController = manageEventsController;
       // System.out.println("Parent controller set to " + this.manageEventsController);
    }


}