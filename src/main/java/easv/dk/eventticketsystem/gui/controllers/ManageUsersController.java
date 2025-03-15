package easv.dk.eventticketsystem.gui.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.BorderPane;

import java.net.URL;
import java.util.ResourceBundle;

public class ManageUsersController implements Initializable {

    @FXML
    private BorderPane usersPane;
    @FXML
    private TableView userTable;
    @FXML
    private TableColumn colName;
    @FXML
    private TableColumn colEmail;
    @FXML
    private TableColumn colRole;
    @FXML
    private TableColumn colActions;


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
