package easv.dk.eventticketsystem.gui.controllers;

import easv.dk.eventticketsystem.be.Users;
import easv.dk.eventticketsystem.gui.model.EventTicketSystemModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.shape.Circle;
import javafx.util.Callback;
import java.util.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.scene.image.Image;
import org.kordamp.ikonli.javafx.FontIcon;
import javafx.scene.paint.Color;


public class ManageUsersController implements Initializable {
    @FXML
    private FlowPane userCardPane;
    @FXML
    private BorderPane usersPane;

    private static final EventTicketSystemModel model = new EventTicketSystemModel();
    private List<Users> usersList;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            loadAllUsers();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void loadAllUsers() throws IOException {
        userCardPane.getChildren().clear();
        List<Users> usersList = model.getAllUsers();
        for (Users user : usersList) {
            // Load the card component (UserCard.fxml) dynamically
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/easv/dk/eventticketsystem/components/UserCard.fxml"));
            AnchorPane card = loader.load();
            // Get the controller of the card and pass the user data
            UserCardController cardController = loader.getController();
            cardController.setUserData(user);
            // Add the card to the FlowPane
            userCardPane.getChildren().add(card);
        }
    }

    private void onEditClicked(Users user) {
        System.out.println("Edit clicked for user: " + user);

    }

    private void onDeleteClicked(Users user) {
        System.out.println("Delete clicked for user: " + user);

    }

    public void onClickAddUser(ActionEvent actionEvent) {
    }
}