package controller;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


import java.io.IOException;
import java.util.Locale;

public class prove extends Application {
    public void start(Stage primaryStage) throws Exception{
        Parent root =FXMLLoader.load(getClass().getResource("aplikimi.fxml "));
        primaryStage.setTitle("hello World");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }
    public static void prove(String[] args){launch(args);}

}
