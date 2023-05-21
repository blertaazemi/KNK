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
        FXMLLoader loader = new FXMLLoader(Aplikimi.class.getResource("aplikimi.fxml"));

        Scene scene = new Scene(loader.load(), 650, 564);
        stage.setTitle("Sistemi Interaktiv për aplikimin dhe menaxhimin e bursave të studentëve\n");
        stage.setScene(scene);
        stage.show();

    }
}

