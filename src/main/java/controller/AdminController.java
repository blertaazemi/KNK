package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.SplitPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class AdminController {
    @FXML
    private Button homebtn;
    @FXML
    private Button studentsbtn;
    @FXML
    private Button bursatbtn;
    @FXML
    private Button aplbtn;
    @FXML
    private Button logBtn;
    @FXML
    private ImageView img1;


    public SplitPane mainSplitPane;
    public AnchorPane currentPane;

    public void initialize(URL url, ResourceBundle resourceBundle) {
        Image bookImage = new Image(getClass().getResourceAsStream("/Images/book.png"));
        img1.setImage(bookImage);}
    

    public void homebtnClick(ActionEvent actionEvent) {
    }

    public void studentsbtnClick(ActionEvent actionEvent) {
        try {
            // Load the FXML file
            Parent root = FXMLLoader.load(getClass().getResource("students.fxml"));

            // Create a new AnchorPane to hold the content of the new scene
            AnchorPane newPane = new AnchorPane(root);

            // Replace the content of the current pane with the new pane
            mainSplitPane.getItems().remove(currentPane);
            mainSplitPane.getItems().add(newPane);
            currentPane = newPane;

//            // Create a new stage
//            Stage stage = new Stage();
//            stage.setTitle("Students"); // Set the title of the new window
//            stage.setScene(new Scene(root));
//
//            // Show the new window
//            stage.show();

            // Close the current window (optional)
           // ((Stage) studentButton.getScene().getWindow()).close();
        } catch (IOException e) {
            e.printStackTrace();
            // Handle the exception properly
        }
    }


    public void bursatbtnClick(ActionEvent actionEvent) {
    }

    public void aplbtnClick(ActionEvent actionEvent) {
        try {
            // Load the FXML file
            Parent root = FXMLLoader.load(getClass().getResource("adminAplikimet.fxml"));

            // Create a new AnchorPane to hold the content of the new scene
            AnchorPane newPane = new AnchorPane(root);

            // Replace the content of the current pane with the new pane
            mainSplitPane.getItems().remove(currentPane);
            mainSplitPane.getItems().add(newPane);
            currentPane = newPane;

//            // Create a new stage
//            Stage stage = new Stage();
//            stage.setTitle("Students"); // Set the title of the new window
//            stage.setScene(new Scene(root));
//
//            // Show the new window
//            stage.show();

            // Close the current window (optional)
            // ((Stage) studentButton.getScene().getWindow()).close();
        } catch (IOException e) {
            e.printStackTrace();
            // Handle the exception properly
        }
    }

    public void logBtnClick(ActionEvent actionEvent) {
    }
}
