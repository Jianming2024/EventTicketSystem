package easv.dk.eventticketsystem.gui.controllers;

import easv.dk.eventticketsystem.MainApplication;
import easv.dk.eventticketsystem.be.Users;
import easv.dk.eventticketsystem.gui.controllers.componentsControllers.EditWindowController;
import easv.dk.eventticketsystem.gui.model.EventTicketSystemModel;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;

import java.awt.*;
import java.awt.MenuItem;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.stage.Stage;

public class ManageEventsController implements Initializable {
    @FXML
    public ContextMenu contextMenu;
    @FXML
    public Button assignDropdown;
    @FXML
    private FlowPane eventCardPane;
    @FXML
    private Button deleteEventBtn;

    private static final EventTicketSystemModel model = new EventTicketSystemModel();
    private List<Event> eventsList;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            loadAllEvents();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    private void loadAllEvents() throws IOException {
        /*eventCardPane.getChildren().clear();
        List<Event> eventsList = model.getAllEvents();
        for (Users user : usersList) {
            // Load the card component (UserCard.fxml) dynamically
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/easv/dk/eventticketsystem/components/EventCard.fxml"));
            AnchorPane card = loader.load();
            // Get the controller of the card and pass the user data
            UserCardController cardController = loader.getController();
            cardController.setUserData(user);
            // Add the card to the FlowPane
            eventCardPane.getChildren().add(card);
        }*/
    }


    public void onAddEvent(ActionEvent actionEvent) {
    }

    public void onEditEvent(ActionEvent actionEvent) {
    }

    public void onDeleteEvent(ActionEvent actionEvent) {
    }


    public void onClickDeleteEvent(ActionEvent actionEvent) {
        Alert confirmationAlert = new Alert(Alert.AlertType.CONFIRMATION);
        confirmationAlert.setTitle("Confirm Deletion");
        confirmationAlert.setHeaderText("Are you sure you want to delete this event?");
        confirmationAlert.setContentText("This action cannot be undone.");

        Optional<ButtonType> result = confirmationAlert.showAndWait();
        if (result.get() == ButtonType.OK) {
        }
    }


    public void onClickTicketType(ActionEvent actionEvent) {
    }

    public void onClickAssign(ActionEvent mouseEvent) {

    }
    public void showConfirmation(ActionEvent actionEvent) {
    }

    public void showEditWindow(ActionEvent actionEvent) {
    }

    public void onClickEditEvent(ActionEvent actionEvent) {
        loadEditWindow();
    }

    private void loadEditWindow() {
            try {
                FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("EditEventView.fxml"));
                Scene scene = new Scene(fxmlLoader.load());
                EditWindowController ew = fxmlLoader.getController();
                Stage stage = new Stage();
                stage.setTitle("Edit Event");
                stage.setScene(scene);
                stage.show();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    public void onMouseClick(ActionEvent actionEvent) {
    }

}
