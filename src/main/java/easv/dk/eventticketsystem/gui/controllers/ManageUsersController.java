package easv.dk.eventticketsystem.gui.controllers;

import easv.dk.eventticketsystem.MainApplication;
import easv.dk.eventticketsystem.be.Users;
import easv.dk.eventticketsystem.gui.controllers.componentsControllers.UserCardController;
import easv.dk.eventticketsystem.gui.model.EventTicketSystemModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;


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

    public void loadAllUsers() throws IOException {
        userCardPane.getChildren().clear();
        List<Users> usersList = model.getAllUsers();
        for (Users user : usersList) {
            // Load the card component (UserCard.fxml) dynamically
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/easv/dk/eventticketsystem/components/UserCard.fxml"));
            AnchorPane userCard = loader.load();
            // Get the controller of the card and pass the user data
            UserCardController cardController = loader.getController();
            // Set the reference to the parent controller
            cardController.setParentController(this);
            cardController.setUserData(user);
            // Add the card to the FlowPane
            userCardPane.getChildren().add(userCard);
            URL resource = getClass().getResource("/easv/dk/eventticketsystem/components/UserCard.fxml");
            if (resource == null) {
                System.err.println("UserCard.fxml resource not found!");
            }
        }
    }

    public void onClickCreateNewUser(ActionEvent actionEvent) throws IOException {
        //System.out.println("Create clicked for user: ");
        FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("/easv/dk/eventticketsystem/UserEditorView.fxml"));
        Parent root = fxmlLoader.load();
        UserEditorController editorController = fxmlLoader.getController();
        editorController.setParentController(this); // 'this' is ManageUsersController instance
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.show();
    }
}