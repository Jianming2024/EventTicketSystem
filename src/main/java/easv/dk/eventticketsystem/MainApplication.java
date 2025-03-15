package easv.dk.eventticketsystem;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.kordamp.bootstrapfx.BootstrapFX;

import java.io.IOException;

public class MainApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        // FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("LoginView.fxml"));
        //Scene scene = new Scene(fxmlLoader.load());
        Parent root = FXMLLoader.load(getClass().getResource("LoginView.fxml"));
        Scene scene = new Scene(root);
        scene.getStylesheets().add(BootstrapFX.bootstrapFXStylesheet());
        // Create an icon
        /*FontIcon icon = new FontIcon("bi-emoji-smile-fill");
        icon.setIconSize(30); // Set size
        icon.setIconColor(javafx.scene.paint.Color.BLUE); // Set color*/

        // Create a button with an icon
        // Button button = new Button("Click Me", icon);


        stage.setTitle("Log in System");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}