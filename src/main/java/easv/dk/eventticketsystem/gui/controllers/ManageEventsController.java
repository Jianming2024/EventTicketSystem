package easv.dk.eventticketsystem.gui.controllers;

import easv.dk.eventticketsystem.be.Users;
import easv.dk.eventticketsystem.gui.model.EventTicketSystemModel;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;

import java.awt.*;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class ManageEventsController implements Initializable {
    @FXML
    private FlowPane eventCardPane;

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


}
