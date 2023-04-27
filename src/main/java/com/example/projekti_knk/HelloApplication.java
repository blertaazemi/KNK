package com.example.projekti_knk;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 320, 240);
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}

//te resources me kriju migration edhe 0001 migration, per cdo ekzekutim te ri krijojme nje new migration file p.sh
//0002-migration, 0003-migration qtj...
//me rujt historine e ekzekutimit te kodit
//historine e migrimeve secili e bartim hap pas hapi ne databazat tona