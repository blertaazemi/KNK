package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.util.ResourceBundle;

public class BursatController implements Initializable {
    @FXML
    private AnchorPane rootPane;

    @FXML
    private Button homeButton;
    @FXML
    private Button studentetButton;
    @FXML
    private Button bursatButton;
    @FXML
    private Button aplikimiButton;
   @FXML
   private Pane bursa1;
    @FXML
    private Pane bursa2;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // Set up initial UI state or perform any initialization tasks here
    }

    @FXML
    private void handleHomeButton(ActionEvent event) {
        // Handle the home button action here
    }

    @FXML
    private void handleStudentetButton(ActionEvent event) {
        // Handle the studentet button action here
    }

    @FXML
    private void handleBursatButton(ActionEvent event) {
        // Handle the bursat button action here
    }
}

