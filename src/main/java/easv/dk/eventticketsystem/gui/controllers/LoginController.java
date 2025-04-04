package easv.dk.eventticketsystem.gui.controllers;

import easv.dk.eventticketsystem.MainApplication;
import easv.dk.eventticketsystem.gui.util.AlertUtil;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.Region;
import javafx.stage.Stage;
import org.kordamp.ikonli.javafx.FontIcon;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class LoginController implements Initializable {
    @FXML
    private Button btnLogin;
    @FXML
    private TextField loginEmail;

    @FXML
    private PasswordField loginPassword;

    @FXML
    private TextField visiblePassword;

    @FXML
    private Button btnTogglePassword;

    @FXML
    private FontIcon eyeIcon;

    private boolean passwordVisible = false;

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
            AlertUtil.showErrorAlert("Login Failed", "Invalid email or password. Please try again.");
        }
    }

    private boolean isValidUser(String email, String password) {
        // Replace with actual authentication logic (e.g., check from database)
        return "admin@gmail.com".equals(email) && "123456".equals(password);
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

    public void onTogglePasswordVisibility(ActionEvent actionEvent) {
        passwordVisible = !passwordVisible;
        if (passwordVisible) {
            // Show plain text field
            visiblePassword.setText(loginPassword.getText());
            visiblePassword.setVisible(true);
            visiblePassword.setManaged(true);
            loginPassword.setVisible(false);
            loginPassword.setManaged(false);
            // Change icon to "eye-slash" if available (adjust literal if needed)
            eyeIcon.setIconLiteral("bi-eye-slash");
        } else {
            // Hide plain text field, show PasswordField again
            loginPassword.setText(visiblePassword.getText());
            loginPassword.setVisible(true);
            loginPassword.setManaged(true);
            visiblePassword.setVisible(false);
            visiblePassword.setManaged(false);
            // Change icon back to "eye"
            eyeIcon.setIconLiteral("bi-eye");
        }

    }
}
