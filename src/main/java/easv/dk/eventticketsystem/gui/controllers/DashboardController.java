package easv.dk.eventticketsystem.gui.controllers;

import easv.dk.eventticketsystem.MainApplication;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class DashboardController implements Initializable {
    public ImageView eventImageView;
    public Label dashboardLabel;
    @FXML
    private Button btnDashboard;
    @FXML
    private Button btnManageUsers;    // Admin only
    @FXML
    private Button btnManageEvents;   // Event Coordinator
    @FXML
    private Button btnPrintTickets;   // Event Coordinator
    @FXML
    private Button btnMyTickets;      // Customer
    @FXML
    private StackPane contentPane;

    private String userRole; // Set this dynamically (Admin, Coordinator, Customer)
    private String userEmail;

    private LoginController loginController;
    private SidebarController sidebarController;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        //loadDashboardView();
    }

    private void loadDashboardView() {
        //contentPane.getChildren().clear(); // Clear current view
        //Label dashboardLabel = new Label("Welcome to the Dashboard!");
        //dashboardLabel.setStyle("-fx-font-size: 24px; -fx-text-fill: black;");
        //contentPane.getChildren().add(dashboardLabel);
    }

    public void setParentController(LoginController parentController) {
        this.loginController = parentController;
    }

    public void setUserRole(String role, String email) {
        this.userRole = role;
        this.userEmail = email;
        checkUserRole();
    }

    private void checkUserRole() {
        if ("Admin".equals(userRole) && "admin@gmail.com".equals(userEmail)) {
            btnDashboard.setVisible(true);
            btnManageUsers.setVisible(true);
            btnManageEvents.setVisible(false);
            btnPrintTickets.setVisible(false);
            btnMyTickets.setVisible(false);
        } else if ("Coordinator".equals(userRole)) {
            btnDashboard.setVisible(true);
            btnManageUsers.setVisible(false);
            btnManageEvents.setVisible(true);
            btnPrintTickets.setVisible(true);
            btnMyTickets.setVisible(false);
        } else if ("Customer".equals(userRole)) {
            btnDashboard.setVisible(true);
            btnManageUsers.setVisible(false);
            btnManageEvents.setVisible(false);
            btnPrintTickets.setVisible(false);
            btnMyTickets.setVisible(true);
        }
    }



}
