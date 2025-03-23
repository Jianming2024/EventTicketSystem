package easv.dk.eventticketsystem.gui.controllers;

import easv.dk.eventticketsystem.be.Users;
import easv.dk.eventticketsystem.gui.model.EventTicketSystemModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.shape.Circle;
import javafx.util.Callback;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.scene.image.Image;
import org.kordamp.ikonli.javafx.FontIcon;
import javafx.scene.paint.Color;


public class ManageUsersController implements Initializable {

    @FXML
    private TableColumn colUserImg;
    @FXML
    private TableColumn colPhone;
    @FXML
    private BorderPane usersPane;
    @FXML
    private TableView userTable;
    @FXML
    private TableColumn colName;
    @FXML
    private TableColumn colEmail;
    @FXML
    private TableColumn colRole;
    @FXML
    private TableColumn colAction;

    private static final EventTicketSystemModel model = new EventTicketSystemModel();


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            loadUsersList();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void loadUsersList() throws IOException {
        userTable.getItems().clear();
        userTable.setItems(model.getAllUsers());
        colName.setCellValueFactory(new PropertyValueFactory<>("userName"));
        colEmail.setCellValueFactory(new PropertyValueFactory<>("userEmail"));
        colRole.setCellValueFactory(new PropertyValueFactory<>("role"));
        colPhone.setCellValueFactory(new PropertyValueFactory<>("userPhone"));
        colUserImg.setCellValueFactory(new PropertyValueFactory<>("userImagePath"));
        // Custom cell factory for the avatar image column
        colUserImg.setCellFactory(new Callback<TableColumn<Users, String>, TableCell<Users, String>>() {
            @Override
            public TableCell<Users, String> call(TableColumn<Users, String> param) {
                return new TableCell<Users, String>() {
                    private final ImageView imageView = new ImageView();
                    {
                        // Set desired image size
                        imageView.setFitWidth(50);
                        imageView.setFitHeight(50);
                        imageView.setPreserveRatio(false);
                        // Clip the image to a circle (radius = 20)
                        Circle clip = new Circle(25, 25, 25);
                        imageView.setClip(clip);
                        // Set the cell's preferred height explicitly
                        setPrefHeight(50);
                        setAlignment(Pos.CENTER);
                    }

                    @Override
                    protected void updateItem(String imagePath, boolean empty) {
                        super.updateItem(imagePath, empty);
                        setPrefHeight(50);
                        if (empty || imagePath == null || imagePath.isBlank()) {
                            setGraphic(null);
                        } else {
                            //System.out.println("DEBUG: Original image path from DB: " + imagePath);

                            // Define the base directory where your images are stored on disk.
                            String baseDir = "/Users/janeyfan/Desktop/Projects/5th Ticket management system/EventTicketSystem/UserImg";
                            String adjustedPath = imagePath;
                            if (adjustedPath.startsWith("/userImg")) {
                                adjustedPath = baseDir + adjustedPath.substring("/userImg".length());
                            }
                            //System.out.println("DEBUG: Adjusted file path: " + adjustedPath);

                            File file = new File(adjustedPath);
                            if (file.exists()) {
                                Image image = new Image(file.toURI().toString());
                                imageView.setImage(image);
                                //System.out.println("DEBUG: Loaded image from file system: " + file.toURI().toString());
                            } else {
                                //System.err.println("DEBUG: File not found: " + adjustedPath);
                                imageView.setImage(null);
                            }
                            setGraphic(imageView);
                        }
                    }
                };
            }
        });

        colAction.setCellFactory(new Callback<TableColumn<Users, Void>, TableCell<Users, Void>>() {
            @Override
            public TableCell<Users, Void> call(TableColumn<Users, Void> param) {
                return new TableCell<Users, Void>() {

                    private final FontIcon editIcon = new FontIcon("bi-pencil-fill");
                    private final FontIcon deleteIcon = new FontIcon("bi-trash-fill");
                    private final HBox container = new HBox(10); // spacing between icons

                    {
                        // Style the icons
                        editIcon.setIconSize(18);
                        editIcon.setIconColor(Color.FORESTGREEN);
                        deleteIcon.setIconSize(18);
                        deleteIcon.setIconColor(Color.INDIANRED);

                        // Set the container's alignment to center
                        //container.setAlignment(javafx.geometry.Pos.CENTER);

                        // Add click handlers
                        editIcon.setOnMouseClicked(e -> {
                            Users user = getTableView().getItems().get(getIndex());
                            onEditClicked(user);
                        });
                        deleteIcon.setOnMouseClicked(e -> {
                            Users user = getTableView().getItems().get(getIndex());
                            onDeleteClicked(user);
                        });

                        container.getChildren().addAll(editIcon, deleteIcon);
                    }

                    @Override
                    protected void updateItem(Void item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty) {
                            setGraphic(null);
                        } else {
                            // Show the HBox with two icons
                            setGraphic(container);
                        }
                    }
                };
            }
        });
    }

    private void onEditClicked(Users user) {
        System.out.println("Edit clicked for user: " + user);

    }

    private void onDeleteClicked(Users user) {
        System.out.println("Delete clicked for user: " + user);

    }

    public void onAddUser(ActionEvent actionEvent) {
    }

    public void onEditUser(ActionEvent actionEvent) {
    }

    public void onDeleteUser(ActionEvent actionEvent) {
    }
}