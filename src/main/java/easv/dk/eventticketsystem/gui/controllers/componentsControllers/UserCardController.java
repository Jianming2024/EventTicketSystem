package easv.dk.eventticketsystem.gui.controllers.componentsControllers;

import easv.dk.eventticketsystem.MainApplication;
import easv.dk.eventticketsystem.be.Users;
import easv.dk.eventticketsystem.gui.controllers.ManageUsersController;
import easv.dk.eventticketsystem.gui.controllers.UserEditorController;
import easv.dk.eventticketsystem.gui.model.EventTicketSystemModel;
import easv.dk.eventticketsystem.gui.util.AlertUtil;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import org.kordamp.ikonli.javafx.FontIcon;

import javax.swing.*;
import java.io.IOException;
import java.io.InputStream;
import java.util.Optional;

public class UserCardController {
    @FXML
    private FontIcon btnEdit;
    @FXML
    private Button btnDelete;
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

    private Users user;
    private final EventTicketSystemModel model = new EventTicketSystemModel();
    private ManageUsersController manageUsersController;

    public void setUserData(Users user) {
        this.user = user;
        lblUserName.setText(user.getUserName());
        lblUserEmail.setText(user.getUserEmail());
        lblRole.setText(user.getRole());
        lblUserPhone.setText(user.getUserPhone());
        String imagePath = user.getUserImagePath(); // e.g., "/userImg/admin.jpg"
        //System.out.println("DEBUG: Attempting to load image from resource: " + imagePath);

        InputStream is = getClass().getResourceAsStream(imagePath);
        if (is != null) {
            avatar.setImage(new Image(is));
            //System.out.println("DEBUG: Loaded image from resource: " + imagePath);
        } else {
            System.err.println("DEBUG: Resource not found: " + imagePath);
            // Optionally load a placeholder image if database no work
            /*InputStream placeholderStream = getClass().getResourceAsStream("/userImg/placeholder.png");
            if (placeholderStream != null) {
                avatar.setImage(new Image(placeholderStream));
            } else {
                avatar.setImage(null);
            }*/
        }
    }

    public void handleDoubleClick(MouseEvent mouseEvent) {
        if (mouseEvent.getClickCount() == 2) {
            System.out.println("Double-clicked on user card");
            // Open edit dialog or perform edit action here
        }
    }

    public void onClickEditUser(ActionEvent actionEvent) throws IOException {
// Load the UserEditorView FXML file
        FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("/easv/dk/eventticketsystem/UserEditorView.fxml"));
        Parent root = fxmlLoader.load();

        // Get the UserEditorController instance from the loader
        UserEditorController editorController = fxmlLoader.getController();
        // Pass the selected user data to the editor controller
        editorController.setUserData(user);
        // pass the parent controller reference if needed
        editorController.setParentController(manageUsersController);

        // Create a new stage for the user editor
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.setTitle("Edit User");
        stage.show();

        if (manageUsersController != null) {
            manageUsersController.loadAllUsers();
        } else {
            System.err.println("Parent controller is not set!");
        }
    }

    public void onClickDeleteUser(ActionEvent actionEvent) throws IOException {
        boolean confirmed = AlertUtil.showConfirmationAlert("Delete User Confirmation",
                "Are you sure you want to delete this user?");
        if (confirmed) {
            model.deleteUsers(user);
            System.out.println("User deleted: " + user.getUserName());
        } else {
            System.out.println("User deletion canceled");
        }
        // Refresh the list of users using the parent controller, if available
        if (manageUsersController != null) {
            manageUsersController.loadAllUsers();
        } else {
            System.err.println("Parent controller is not set!");
        }
    }

    public void setParentController(ManageUsersController manageUsersController) {
        this.manageUsersController = manageUsersController;
    }
}
