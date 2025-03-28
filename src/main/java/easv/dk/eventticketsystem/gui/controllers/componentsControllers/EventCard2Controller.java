package easv.dk.eventticketsystem.gui.controllers.componentsControllers;

import easv.dk.eventticketsystem.be.Event;
import easv.dk.eventticketsystem.gui.model.EventTicketSystemModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;

public class EventCard2Controller {
    @FXML
    private ImageView eventImage;
    @FXML
    private Label lblEventName;
    @FXML
    private Label lblLocation;
    @FXML
    private Label lblStartTime;
    @FXML
    private Label lblEndTime;
    @FXML
    private Label lblEventPrice;
    @FXML
    private Label lblInformation;

    private static final EventTicketSystemModel model = new EventTicketSystemModel();

    public void setEventData(Event event) {
        lblEventName.setText(event.getEventName());
        lblLocation.setText(event.getLocation());
        lblStartTime.setText(event.getStartDatetime().toString());
        lblEndTime.setText(event.getEndDatetime().toString());

    }

    public void onClickEdit(ActionEvent actionEvent) {
    }

    public void onClickDelete(ActionEvent actionEvent) {
    }

    public void setDataPlaceholder() {
    }
}
