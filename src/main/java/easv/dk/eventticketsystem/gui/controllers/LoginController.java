package easv.dk.eventticketsystem.gui.controllers;

import easv.dk.eventticketsystem.MainApplication;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.Region;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class LoginController implements Initializable {
    @FXML
    private Button btnLogin;
    @FXML
    private TextField loginEmail;
    @FXML
    private TextField loginPassword;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }

    @FXML
    private void onLoginBtnClick(ActionEvent actionEvent) throws IOException {
        String email = loginEmail.getText();
        String password = loginPassword.getText();
        String userRole = authenticateUser(email, password);

        if (isValidUser(email, password)) {
            Stage currentStage = (Stage) btnLogin.getScene().getWindow();
            currentStage.close();
            loadDashboardView(userRole, email);
        } else {
            showBootstrapAlert("Login Failed", "Invalid email or password. Please try again.");
        }
    }

    private boolean isValidUser(String email, String password) {
        // Replace with actual authentication logic (e.g., check from database)
        return "admin@gmail.com".equals(email) && "123456".equals(password);
    }

    private void showBootstrapAlert(String title, String message) {
        Alert alert;
        alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);

        // Apply BootstrapFX style
        alert.getDialogPane().getStylesheets().add(getClass().getResource("/org/kordamp/bootstrapfx/bootstrapfx.css").toExternalForm());
        alert.getDialogPane().getStyleClass().add("alert-danger"); // BootstrapFX class for error alerts

        alert.getDialogPane().setMinHeight(Region.USE_PREF_SIZE); // Prevents text cut-off
        alert.showAndWait();
    }

    private String authenticateUser(String email, String password) {
        if ("admin@gmail.com".equals(email) && "123456".equals(password)) {
            return "Admin";
        } else if ("coordinator@gmail.com".equals(email) && "123456".equals(password)) {
            return "Coordinator";
        } else if ("customer@gmail.com".equals(email) && "123456".equals(password)) {
            return "Customer";
        }
        return null; // Invalid credentials
    }

    private void loadDashboardView(String role, String email) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("DashboardView.fxml"));
            Scene scene = new Scene(fxmlLoader.load());
            DashboardController dboard = fxmlLoader.getController();
            dboard.setParentController(this);
            Stage stage = new Stage();
            stage.setTitle("Event Ticket System");
            stage.setScene(scene);
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
