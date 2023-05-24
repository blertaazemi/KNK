package controller;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Locale;

public class bursat2 extends Application {

    public void start(Stage stage) throws IOException {

        // Set default locale to English or any other language

        Locale.setDefault(new Locale("en", "KS"));
        Locale.setDefault(new Locale("sq", "KS"));

        FXMLLoader fxmlLoader = new FXMLLoader(bursat2.class.getResource("bursat2.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("\n");
        stage.setScene(scene);
        stage.show();
    }


}