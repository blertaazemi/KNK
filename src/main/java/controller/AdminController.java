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

            Parent root = FXMLLoader.load(getClass().getResource("students.fxml"));

            AnchorPane newPane = new AnchorPane(root);

            mainSplitPane.getItems().remove(currentPane);
            mainSplitPane.getItems().add(newPane);
            currentPane = newPane;

        } catch (IOException e) {
            e.printStackTrace();

        }
    }


    public void bursatbtnClick(ActionEvent actionEvent) {
    }

    public void aplbtnClick(ActionEvent actionEvent) {
        try {

            Parent root = FXMLLoader.load(getClass().getResource("adminAplikimet.fxml"));

            AnchorPane newPane = new AnchorPane(root);

            mainSplitPane.getItems().remove(currentPane);
            mainSplitPane.getItems().add(newPane);
            currentPane = newPane;


        } catch (IOException e) {
            e.printStackTrace();

        }
    }

    public void logBtnClick(ActionEvent actionEvent) {
        try {

            Parent root = FXMLLoader.load(getClass().getResource("adminlogin.fxml"));

            Stage stage = new Stage();
            stage.setTitle("Students"); // Set the title of the new window
            stage.setScene(new Scene(root));

            stage.show();
             ((Stage) logBtn.getScene().getWindow()).close();
        } catch (IOException e) {
            e.printStackTrace();

        }
    }
}
