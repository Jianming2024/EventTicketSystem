package easv.dk.eventticketsystem.gui.controllers;

import easv.dk.eventticketsystem.MainApplication;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class DashboardController implements Initializable {
    @FXML
    private ImageView eventImageView;
    @FXML
    private Label dashboardLabel;
    @FXML
    private LineChart salesChart;
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

    private String userRole; // Set this dynamically (Admin, Coordinator)
    private String userEmail;

    private LoginController loginController;
    private SidebarController sidebarController;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        loadDashboardView();
    }

    private void loadDashboardView() {
        XYChart.Series<String, Number> series = new XYChart.Series<>();
        series.setName("Ticket Sales");

        // Use month names for the x-axis categories
        series.getData().add(new XYChart.Data<>("January", 100));
        series.getData().add(new XYChart.Data<>("February", 120));
        series.getData().add(new XYChart.Data<>("March", 80));
        series.getData().add(new XYChart.Data<>("April", 150));
        series.getData().add(new XYChart.Data<>("May", 200));
        series.getData().add(new XYChart.Data<>("June", 180));
        series.getData().add(new XYChart.Data<>("July", 220));
        series.getData().add(new XYChart.Data<>("August", 210));
        series.getData().add(new XYChart.Data<>("September", 190));
        series.getData().add(new XYChart.Data<>("October", 230));
        series.getData().add(new XYChart.Data<>("November", 170));
        series.getData().add(new XYChart.Data<>("December", 250));
        salesChart.getData().add(series);
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
