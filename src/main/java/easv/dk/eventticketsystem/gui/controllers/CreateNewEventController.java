package easv.dk.eventticketsystem.gui.controllers;

import easv.dk.eventticketsystem.gui.controllers.componentsControllers.EventCard2Controller;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;

public class CreateNewEventController {
    public void onClickBrowseAvatar(MouseEvent mouseEvent) {
      }
    @FXML
    private Button btnCreate;
    @FXML
    private Button btnCancel;


    public void onClickCancel(ActionEvent actionEvent) {
        Stage stage = (Stage) btnCancel.getScene().getWindow();
        stage.close();
    }

    /*public void onClickCreateEvent() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/easv/dk/eventticketsystem/components/EventCard2.fxml"));
            Parent card = loader.load();

            EventCard2Controller controller = loader.getController();
            controller.setDataPlaceholder(); // Shows placeholder text like "New Order"

            eventCardPane.getChildren().add(card);
        } catch (IOException e) {
            e.printStackTrace();
        }*/

    }

