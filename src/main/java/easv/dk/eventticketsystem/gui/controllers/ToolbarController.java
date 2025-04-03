package easv.dk.eventticketsystem.gui.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class ToolbarController {
    @FXML
    private TextField txtQuery;
    @FXML
    private Label lblUserName;

    public void handleSearch(ActionEvent actionEvent) {
        String query = txtQuery.getText();
        System.out.println("Searching for: " + query);
    }
}
