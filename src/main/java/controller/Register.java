package controller;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;


import java.io.IOException;
import java.util.Locale;
import java.util.ResourceBundle;


public class Register extends Application {
    @Override
    public void start(Stage stage) throws IOException {

        //set default locALE TO ENGLISH ose ndonje gjuhe tjeter


        //Locale.setDefault(new Locale("en", "KS"));
        //Locale.setDefault(new Locale("sq", "KS"));

        FXMLLoader fxmlLoader = new FXMLLoader(Register.class.getResource("register.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 650, 564);
        stage.setTitle("Sistemi Interaktiv për aplikimin dhe menaxhimin e bursave të studentëve\n");
        stage.setScene(scene);
        stage.show();

    }
}