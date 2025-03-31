package easv.dk.eventticketsystem.gui.controllers;

import easv.dk.eventticketsystem.be.Users;
import easv.dk.eventticketsystem.gui.model.EventTicketSystemModel;
import easv.dk.eventticketsystem.gui.util.AlertUtil;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;

public class UserEditorController {
    @FXML
    private StackPane avatarUploadBox;
    @FXML
    private Label lblUploadAvatar;
    @FXML
    private TextField txtUsername;
    @FXML
    private TextField txtEmail;
    @FXML
    private TextField txtPhone;
    @FXML
    private ComboBox<String> comboRole;
    @FXML
    private Button btnCreate;
    @FXML
    private Button btnCancel;

    private Users user;
    private EventTicketSystemModel model;
    private String userImagePath;

    public void onClickCancel(ActionEvent actionEvent) {
        Stage stage = (Stage) btnCancel.getScene().getWindow();
        stage.close();
    }

    public void onClickCreateUser(ActionEvent actionEvent) throws IOException {
        String userName = txtUsername.getText();
        String email = txtEmail.getText();
        String phone = txtPhone.getText();
        String role = comboRole.getValue();
        String path = (userImagePath != null) ? userImagePath : "";
        if (userName.isEmpty() || email.isEmpty() || phone.isEmpty() || role.isEmpty()) {
            AlertUtil.showErrorAlert("Fail to create a new user", "Username or email or phone is empty");
        } else {
            Users newUser = new Users(0, userName, path, role, email, phone);
            model.createNewUsers(newUser);

        }
    }

    public void onClickBrowseAvatar(MouseEvent actionEvent) {
        // Open a FileChooser to select an image
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Select Avatar Photo");
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.jpeg")
        );
        // Show the chooser
        File selectedFile = fileChooser.showOpenDialog(avatarUploadBox.getScene().getWindow());
        if (selectedFile != null) {
            // Set the class-level variable
            userImagePath = selectedFile.getAbsolutePath();
            System.out.println("Selected avatar: " + userImagePath);
            lblUploadAvatar.setText("Selected: " + selectedFile.getName());
        }
    }
}
