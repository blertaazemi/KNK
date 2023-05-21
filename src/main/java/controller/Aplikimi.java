package controller;

<<<<<<< Updated upstream

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
=======
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Locale;


public class Aplikimi extends Application {
    @Override
    public void start(Stage stage) throws IOException {

        //set default locALE TO ENGLISH ose ndonje gjuhe tjeter

        Locale.setDefault(new Locale("en","KS"));
        Locale.setDefault(new Locale("de","KS"));

        FXMLLoader fxmlLoader = new FXMLLoader(Aplikimi.class.getResource("aplikimi.fxml"));

        Scene scene = new Scene(fxmlLoader.load(), 650, 564);
        stage.setTitle("Sistemi Interaktiv për aplikimin dhe menaxhimin e bursave të studentëve\n");
>>>>>>> Stashed changes
        stage.setScene(scene);
        stage.show();
    }

<<<<<<< Updated upstream
    public static void main(String[] args) {
        launch(args);
    }

}

=======

}
>>>>>>> Stashed changes
