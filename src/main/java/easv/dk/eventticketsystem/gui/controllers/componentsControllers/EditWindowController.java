package easv.dk.eventticketsystem.gui.controllers.componentsControllers;

import easv.dk.eventticketsystem.be.Event;
import easv.dk.eventticketsystem.gui.controllers.ManageEditWindow;
import easv.dk.eventticketsystem.gui.controllers.ManageEventsController2;
import easv.dk.eventticketsystem.gui.model.EventTicketSystemModel;
import easv.dk.eventticketsystem.gui.util.AlertUtil;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.StackPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import javax.swing.text.html.ImageView;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.time.LocalDateTime;
import java.util.ResourceBundle;


public class EditWindowController implements Initializable {

    @FXML
    public Button btnCancel;
    @FXML
    public Button btnSaveChanges;
    @FXML
    public ImageView avatar;
    @FXML
    public TextField txtLocation;
    @FXML
    public ComboBox comboAssign;
    @FXML
    public TextArea txtAreaDescription;
    @FXML
    public Label lblUploadAvatar;
    @FXML
    public TextField txtEventName;
    @FXML
    public TextField txtStartTime;
    @FXML
    public TextField txtEndTime;
    @FXML
    private StackPane avatarUploadBox;

    private final EventTicketSystemModel model = new EventTicketSystemModel();

    private EventCard2Controller parentController; // Store reference to main controller

    private String eventImagePath;

    private Event currentEvent;


    public void setEvent(Event event) {
        this.currentEvent = event;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void onClickBrowseAvatar(MouseEvent mouseEvent) {
        String imgPath = (eventImagePath != null) ? eventImagePath : "";
        // Open a FileChooser to select an image
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Select Avatar Photo");
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.jpeg")
        );
        // Show the chooser
        File selectedFile = fileChooser.showOpenDialog(avatarUploadBox.getScene().getWindow());
        if (selectedFile != null) {
            try {
                File destDir = new File("eventImg");  // Folder at the project root (make sure it's added to your repository)
                if (!destDir.exists()) {

                    destDir.mkdirs();
                }
                // Create destination file with the same name
                File destFile = new File(destDir, selectedFile.getName());
                // Copy file (replace if already exists)
                Files.copy(selectedFile.toPath(), destFile.toPath(), StandardCopyOption.REPLACE_EXISTING);
                // Set the relative path (so all team members refer to the same resource)
                String eventImagePath = "/eventImg/" + selectedFile.getName();
                lblUploadAvatar.setText("Selected: " + selectedFile.getName());
                System.out.println("DEBUG: Copied avatar to: " + destFile.getAbsolutePath());
            } catch (IOException ex) {
                ex.printStackTrace();
                AlertUtil.showErrorAlert("Error", "Failed to copy avatar image.");
            }
        }
    }

    public void onClickCancel(ActionEvent actionEvent) {
        Stage stage = (Stage) btnCancel.getScene().getWindow();
        stage.close();
    }
    public void onClickSaveChanges(ActionEvent actionEvent) throws IOException {
        try {
            // Retrieve values from form fields
            String eventName = txtEventName.getText();
            LocalDateTime startDatetime = LocalDateTime.parse(txtStartTime.getText());
            LocalDateTime endDatetime = LocalDateTime.parse(txtEndTime.getText());
            String location = txtLocation.getText();
            String notes = txtAreaDescription.getText();
            String imgPath = lblUploadAvatar.getText();
            if (currentEvent == null) {
                AlertUtil.showErrorAlert("Error", "No event selected.");
                return;
            }
            // Validation
            if (eventName.isEmpty() || location.isEmpty() || notes.isEmpty()) {
                AlertUtil.showErrorAlert("Failed to edit event", "All fields are required.");
                return;
            } //time and date cannot be empty, but .isEmpty(); does not work
            // Get the selected event ID (if it's an edit)
            Event selectedEvent = currentEvent; // Store current event when opening edit form
            if (selectedEvent == null) {
                AlertUtil.showErrorAlert("Failed to edit event", "No event selected.");
                return;
            }
            // Update event details
            selectedEvent.setEventName(eventName);
            selectedEvent.setStartDatetime(startDatetime);
            selectedEvent.setEndDatetime(endDatetime);
            selectedEvent.setLocation(location);
            selectedEvent.setNotes(notes);
            selectedEvent.setEventImagePath(imgPath);
            // Call database update method
            model.updateEvent(selectedEvent);

            if (parentController != null) {
                parentController.refreshEventData(currentEvent);
            }

            // Close the edit window
            Stage stage = (Stage) btnSaveChanges.getScene().getWindow();
            stage.close();
            AlertUtil.showInfoAlert("Success", "Event updated successfully.");
        } catch (Exception e) {
            AlertUtil.showErrorAlert("Error", "Failed to update event: " + e.getMessage());
        }
    }

    public void loadEventData(Event event) {
        if (event != null) {
            currentEvent = event;// Store current event for saving
            txtEventName.setText(event.getEventName());
            txtStartTime.setText(event.getStartDatetime().toString());
            txtEndTime.setText(event.getEndDatetime().toString());
            txtLocation.setText(event.getLocation());
            txtAreaDescription.setText(event.getNotes());
            lblUploadAvatar.setText(event.getEventImagePath());
        }
    }
    public void setParentController(EventCard2Controller parentController) {
        this.parentController = parentController;
    }
}



