package easv.dk.eventticketsystem.gui.util;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Window;
import javafx.util.Duration;
import org.controlsfx.control.Notifications;
import org.kordamp.ikonli.javafx.FontIcon;

import java.awt.*;

import static java.awt.Color.*;

public final class NotificationUtil {
    private static Runnable onConfirm;

    private NotificationUtil() {
        // Private constructor to prevent instantiation
    }

    public static void showSuccessNotification(Window owner, String title, String message) {
        Label content = createNotificationContent(title, message, "black");
        Notifications.create()
                .owner(owner)
                .graphic(content)
                .hideAfter(Duration.seconds(5))
                .position(Pos.BOTTOM_CENTER)
                // Clear default title and text since we are using our custom graphic.
                .title("")
                .text("")
                .show();
    }

    public static void showErrorNotification(Window owner, String title, String message) {
        Label content = createNotificationContent(title, message, "black");
        Notifications.create()
                .owner(owner)
                .graphic(content)
                .hideAfter(Duration.seconds(5))
                .position(Pos.BOTTOM_CENTER)
                .title("")
                .text("")
                .show();
    }

    public static void showWarningNotification(Window owner, String title, String message) {
        VBox content = createWarningNotificationContent(title, message, "black", onConfirm); // Warm orange background
        Notifications.create()
                .owner(owner)
                .graphic(content)
                .hideAfter(Duration.seconds(5)) // Allow a bit more time for deletion confirmation
                .position(Pos.BOTTOM_CENTER)
                .title("")
                .text("")
                .showWarning();
    }

    private static Label createNotificationContent(String title, String message, String textColor) {
        Label label = new Label(title + ": " + message);
        // Set a transparent background, desired text color and font styling.
        label.setStyle("-fx-background-color: rgba(0, 0, 0, 0.05);" +  // Subtle light background
                "-fx-text-fill: " + textColor + ";" +
                "-fx-font-size: 16px;" +
                "-fx-padding: 15px 20px;" +    // Increase vertical padding for a higher label
                "-fx-background-radius: 5px;"   // Rounded corners with radius 5px
        );
        return label;
    }

    private static VBox createWarningNotificationContent(String title, String message, String backgroundColor, Runnable onConfirm) {
        VBox container = new VBox(10); // 10px spacing
        container.setAlignment(Pos.CENTER);

        // Main warning message
        Label mainLabel = new Label(title + ": " + message);
        mainLabel.setStyle("-fx-text-fill: white; -fx-font-size: 14px; -fx-font-weight: bold;");

        // Create trash icon using FontIcon (Ikonli)
        FontIcon trashIcon = new FontIcon("bi-trash-fill");
        trashIcon.setIconSize(24);
        //trashIcon.setIconColor("#000000");

        // "Confirm Delete" text below the icon
        Label confirmLabel = new Label("Confirm Delete");
        confirmLabel.setStyle("-fx-text-fill: white; -fx-font-size: 12px;");

        // Container for the icon and text
        VBox iconBox = new VBox(5);
        iconBox.setAlignment(Pos.CENTER);
        iconBox.getChildren().addAll(trashIcon, confirmLabel);
        // Make the iconBox clickable
        iconBox.setOnMouseClicked(e -> {
            if (onConfirm != null) {
                onConfirm.run();
            }
        });

        container.getChildren().addAll(mainLabel, iconBox);
        container.setStyle("-fx-background-color: " + backgroundColor + ";" +
                "-fx-background-radius: 5px;" +
                "-fx-padding: 15px 20px;");
        return container;
    }

}
