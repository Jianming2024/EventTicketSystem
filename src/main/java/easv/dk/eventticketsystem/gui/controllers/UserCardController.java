package easv.dk.eventticketsystem.gui.controllers;

import easv.dk.eventticketsystem.be.Users;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

import java.io.File;
import java.io.InputStream;

public class UserCardController {
    @FXML
    private ImageView avatar;
    @FXML
    private Label lblUserName;
    @FXML
    private Label lblUserEmail;
    @FXML
    private Label lblUserPhone;
    @FXML
    private Label lblRole;

    public void setUserData(Users user) {
        lblUserName.setText(user.getUserName());
        lblUserEmail.setText(user.getUserEmail());
        lblRole.setText(user.getRole());
        lblUserPhone.setText(user.getUserPhone());
        String imagePathFromDb = user.getUserImagePath();
        // If the DB already contains the full absolute path, you don't need to adjust it.
        // If not, we adjust the relative path by prepending the actual directory.
        String adjustedPath = imagePathFromDb;
        if (imagePathFromDb.startsWith("/userImg")) {
            // Replace with your actual base directory
            String baseDir = "/Users/janeyfan/Desktop/Projects/5th Ticket management system/EventTicketSystem/UserImg";
            adjustedPath = baseDir + imagePathFromDb.substring("/userImg".length());
        }

        //System.out.println("DEBUG: Adjusted image path: " + adjustedPath);
        File file = new File(adjustedPath);
        if (file.exists()) {
            avatar.setImage(new Image(file.toURI().toString()));
        } else {
            System.err.println("DEBUG: File not found: " + adjustedPath);
            // Load a placeholder image from resources (if available)
            InputStream placeholderStream = getClass().getResourceAsStream("/easv/dk/eventticketsystem/views/images/placeholder.png");
            if (placeholderStream != null) {
                avatar.setImage(new Image(placeholderStream));
            } else {
                avatar.setImage(null);
            }
        }
    }

    public void handleDoubleClick(MouseEvent mouseEvent) {
        if (mouseEvent.getClickCount() == 2) {
            System.out.println("Double-clicked on user card");
            // Open edit dialog or perform edit action here
        }
    }

    public void onEditClick(ActionEvent actionEvent) {
    }

    public void onDeleteClick(ActionEvent actionEvent) {
    }
}
