package sio.devoir3sio1b;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Devoir3Application extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Devoir3Application.class.getResource("devoir3-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Devoir n°3");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}