package easv.dk.eventticketsystem.gui.controllers.componentsControllers;

import easv.dk.eventticketsystem.MainApplication;
import easv.dk.eventticketsystem.gui.controllers.ManageEventsController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import javafx.scene.control.*;
import javafx.stage.Stage;

import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;


public class EditWindowController implements Initializable {
    @FXML
    public Button cancelEditBtn;
    @FXML
    public Button saveEditBtn;
    @FXML
    private TextField editTitle;
    @FXML
    private DatePicker editDate;
    @FXML
    private TextField editLocation;
    @FXML
    private TextField editDescription;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void onClickSaveEditBtn(ActionEvent actionEvent) {
    }

    public void onClickCancelEditBtn(ActionEvent actionEvent) {
        Alert confirmationAlert = new Alert(Alert.AlertType.CONFIRMATION);
        confirmationAlert.setTitle("Confirm Cancellation");
        confirmationAlert.setHeaderText("Are you sure you want to cancel the changes?");
        confirmationAlert.setContentText("This action cannot be undone.");

        Optional<ButtonType> result = confirmationAlert.showAndWait();
        if (result.get() == ButtonType.OK) {
        }
    }
}



