package easv.dk.eventticketsystem.gui.controllers;

import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;

import java.awt.*;
import java.net.URL;
import java.util.ResourceBundle;

public class ManageEventsController implements Initializable {
    @FXML
    private TableView eventsTable;
    @FXML
    private TableColumn colId;
    @FXML
    private TableColumn colName;
    @FXML
    private TableColumn colDate;
    @FXML
    private TableColumn colDate1;
    @FXML
    private TableColumn colVenue;
    @FXML
    private TableColumn colCapacity;
    @FXML
    private TableColumn colThumbnail;

    private DashboardController dashboardController;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colDate.setCellValueFactory(new PropertyValueFactory<>("startTime"));
        colDate1.setCellValueFactory(new PropertyValueFactory<>("endTime"));
        colVenue.setCellValueFactory(new PropertyValueFactory<>("location"));
        colCapacity.setCellValueFactory(new PropertyValueFactory<>("ticketType"));
        colThumbnail.setCellValueFactory(new PropertyValueFactory<>("thumbnail"));
        colThumbnail.setCellFactory(column -> new TableCell<Event, Image>() {
            /*private final ImageView imageView = new ImageView();

            @Override
            protected void updateItem(Image item, boolean empty) {
                super.updateItem(item, empty);
                if (empty || item == null) {
                    setGraphic(null);
                } else {
                    imageView.setImage(javafx.scene.image.Image.fromPlatformImage(item));
                    imageView.setFitWidth(50);
                    imageView.setFitHeight(50);
                    imageView.setPreserveRatio(true);
                    setGraphic(imageView);
                }
            }*/
        });

    }




    public void onAddEvent(ActionEvent actionEvent) {
    }

    public void onEditEvent(ActionEvent actionEvent) {
    }

    public void onDeleteEvent(ActionEvent actionEvent) {
    }


}
