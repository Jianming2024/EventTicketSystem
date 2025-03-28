package easv.dk.eventticketsystem.gui.controllers;

import easv.dk.eventticketsystem.be.Users;
import easv.dk.eventticketsystem.gui.model.EventTicketSystemModel;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
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
import java.util.ResourceBundle;
import javafx.event.ActionEvent;

public class ManageEventsController implements Initializable {
    @FXML
    public ContextMenu contextMenu;
    @FXML
    public Button assignDropdown;
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


    public void onClickDeleteEvent(ActionEvent actionEvent) {
        
    }

    public void onClickEditEvent(ActionEvent actionEvent) {
    }

    public void onClickTicketType(ActionEvent actionEvent) {
    }

    public void onClickAssign(ActionEvent mouseEvent) {

    }

    public void onMouseClick(ActionEvent actionEvent) {
    }
}

