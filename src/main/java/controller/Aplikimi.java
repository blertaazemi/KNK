package controller;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import java.io.IOException;

public class Aplikimi extends Application {

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("aplikimi.fxml"));
        AnchorPane root = loader.load();
        Scene scene = new Scene(root, 650, 564);
        stage.setTitle("Aplikimii Controller");

    }
}

