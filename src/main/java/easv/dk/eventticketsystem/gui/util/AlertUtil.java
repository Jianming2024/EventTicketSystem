package easv.dk.eventticketsystem.gui.util;

import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.layout.Region;

public class AlertUtil {
    public static void showSuccessAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);

        // Load BootstrapFX stylesheet (ensure the path is correct)
        alert.getDialogPane().getStylesheets().add(AlertUtil.class.getResource("/org/kordamp/bootstrapfx/bootstrapfx.css").toExternalForm());
        // Set the style class for success (green background)
        alert.getDialogPane().getStyleClass().add("alert-success");

        // Force the dialog to size to its content
        alert.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);

        // Style the OK button to make it clear and visible
        Button okButton = (Button) alert.getDialogPane().lookupButton(ButtonType.OK);
        if (okButton != null) {
            okButton.setStyle(
                    "-fx-border-color: #009640; " +
                    "-fx-border-width: 2; " +
                    "-fx-border-style: solid; " +
                    "-fx-text-fill: black;" +
                    "-fx-border-radius: 5") ;
        }
        alert.showAndWait();
    }

    public static void showErrorAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);

        alert.getDialogPane().getStylesheets().add(AlertUtil.class.getResource("/org/kordamp/bootstrapfx/bootstrapfx.css").toExternalForm());
        alert.getDialogPane().getStyleClass().add("alert-danger");
        alert.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
        Button okButton = (Button) alert.getDialogPane().lookupButton(ButtonType.OK);
        if (okButton != null) {
            okButton.setStyle(
                    "-fx-border-color: #c00d0d; " +
                    "-fx-border-width: 2; " +
                    "-fx-border-style: solid; " +
                    "-fx-text-fill: black;" +
                    "-fx-border-radius: 5") ;
        }
        alert.showAndWait();
    }

    public static void showInfoAlert(String success, String s) {
    }
}
