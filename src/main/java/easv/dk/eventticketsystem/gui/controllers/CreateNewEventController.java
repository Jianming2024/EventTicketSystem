package easv.dk.eventticketsystem.gui.controllers;

import easv.dk.eventticketsystem.be.Event;
import easv.dk.eventticketsystem.gui.controllers.componentsControllers.EventCard2Controller;
import easv.dk.eventticketsystem.gui.model.EventTicketSystemModel;
import easv.dk.eventticketsystem.gui.util.AlertUtil;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class CreateNewEventController {
    public Label lblAvatarPrompt;
    @FXML
    private ImageView avatarImageView;
    @FXML
    private StackPane avatarUploadBox;
    @FXML
    private Label lblUploadAvatar;
    @FXML
    private TextField txtEventName;
    @FXML
    private DatePicker txtDate;
    @FXML
    private TextField txtStartTime;
    @FXML
    private TextField txtEndTime;
    @FXML
    private TextField txtLocation;
    @FXML
    private TextArea txtDescription;
    private String avatarImagePath;

    @FXML
    private Button btnCreate;
    @FXML
    private Button btnCancel;

    private String price;
    private String locationG;
    private String eventImagePath;
    private ManageEventsController2 manageEventsController2;
    private final EventTicketSystemModel model = new EventTicketSystemModel();
    private Event event;
    private static final DateTimeFormatter TIME_FORMATTER = DateTimeFormatter.ofPattern("HH:mm");;


    public void onClickBrowseAvatar(MouseEvent mouseEvent) {
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
                eventImagePath = "/eventImg/" + selectedFile.getName();
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


    public void onClickCreateEvent(ActionEvent actionEvent) throws IOException {

        String eventName = txtEventName.getText();
        LocalDate date = txtDate.getValue();
        String startTimeStr = txtStartTime.getText();
        String endTimeStr = txtEndTime.getText();
        String location = txtLocation.getText();
        String eventImagePath = avatarImagePath != null ? avatarImagePath : "N/A";
        String notes = txtDescription.getText();

        // Step 1: Check for required fields
        if (eventName.isEmpty() || date == null || startTimeStr.isEmpty() || endTimeStr.isEmpty() || location.isEmpty()) {
            AlertUtil.showErrorAlert("Fail to create a new event", "Please fill in all required fields");
            return;
        }

        // ✅ Step 2: Validate time format (this is where you add it)
        if (!startTimeStr.matches("^\\d{2}:\\d{2}$") || !endTimeStr.matches("^\\d{2}:\\d{2}$")) {
            AlertUtil.showErrorAlert("Invalid Time Format", "Please enter time in HH:mm format (e.g. 14:30)");
            return;
        }

        try {
            // Step 3: Safely parse the times
            LocalTime startTime = LocalTime.parse(startTimeStr, TIME_FORMATTER);
            LocalTime endTime = LocalTime.parse(endTimeStr, TIME_FORMATTER);

            LocalDateTime startDateTime = LocalDateTime.of(date, startTime);
            LocalDateTime endDateTime = LocalDateTime.of(date, endTime);

            if (endDateTime.isBefore(startDateTime)) {
                AlertUtil.showErrorAlert("Invalid Time", "End time must be after start time.");
                return;
            }

            Event newEvent = new Event(
                    0,
                    eventName,
                    startDateTime,
                    endDateTime,
                    location,
                    notes,
                    eventImagePath
            );

            model.createNewEvent(newEvent);

            if (manageEventsController2 != null) {
                manageEventsController2.loadAllEvents();
            }

            ((Stage) btnCreate.getScene().getWindow()).close();

        } catch (Exception e) {
            AlertUtil.showErrorAlert("Fail to create a new event", e.getMessage());
        }

    }
    public void setParentController(ManageEventsController2 manageEventsController2) {
        this.manageEventsController2 = manageEventsController2;
    }
}

        /*try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/easv/dk/eventticketsystem/components/EventCard2.fxml"));
            Parent card = loader.load();

            EventCard2Controller controller = loader.getController();
            controller.setDataPlaceholder(); // Shows placeholder text like "New Order"

            eventCardPane.getChildren().add(card);
        } catch (IOException e) {
            e.printStackTrace();
        }*/