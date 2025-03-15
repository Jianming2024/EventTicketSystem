package easv.dk.eventticketsystem.gui.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.layout.BorderPane;

import java.net.URL;
import java.util.ResourceBundle;

public class ManageUsersController implements Initializable {

    public BorderPane usersPane;
    private DashboardController dashboarController;

    public void setParentController(DashboardController parentController) {
        this.dashboarController = parentController;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }

    public void onSearchUser(ActionEvent actionEvent) {
    }

    public void onAddUser(ActionEvent actionEvent) {
    }

    public void onEditUser(ActionEvent actionEvent) {
    }

    public void onDeleteUser(ActionEvent actionEvent) {
    }

}
