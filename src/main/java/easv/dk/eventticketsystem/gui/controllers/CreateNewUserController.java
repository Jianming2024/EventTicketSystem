package easv.dk.eventticketsystem.gui.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class CreateNewUserController {
    @FXML
    private Button btnCreate;
    @FXML
    private Button btnCancel;

    public void onClickCancel(ActionEvent actionEvent) {
        Stage stage = (Stage) btnCancel.getScene().getWindow();
        stage.close();
    }

    public void onClickCreateUser(ActionEvent actionEvent) {
    }

    public void onClickBrowseAvatar(MouseEvent actionEvent) {
    }
}
