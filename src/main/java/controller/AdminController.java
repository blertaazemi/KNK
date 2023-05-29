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
import java.util.Locale;
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

    private ResourceBundle bundle;

    public void initialize(URL url, ResourceBundle resourceBundle) {
        Image bookImage = new Image(getClass().getResourceAsStream("/Images/book.png"));
        img1.setImage(bookImage);
        Locale locale = Locale.getDefault();
       ResourceBundle translate = ResourceBundle.getBundle("translations.content", locale);}


    private void translateElements() {
        homebtn.setText(bundle.getString("homeButton"));
        studentsbtn.setText(bundle.getString("studentsButton"));
        bursatbtn.setText(bundle.getString("bursatButton"));
        aplbtn.setText(bundle.getString("aplButton"));
        logBtn.setText(bundle.getString("logButton"));

    }

    public void translateEn(ActionEvent event){
        Locale.setDefault(new Locale("en"));
        bundle = ResourceBundle.getBundle("translations.content", Locale.getDefault());
        this.translateElements();
    }

    public void translateAl(ActionEvent event){
        Locale.setDefault(new Locale("sq"));
        bundle = ResourceBundle.getBundle("translations.content", Locale.getDefault());
        this.translateElements();
    }
    

    public void homebtnClick(ActionEvent actionEvent) {
        try {

            Parent root = FXMLLoader.load(getClass().getResource("home.fxml"));

            AnchorPane newPane = new AnchorPane(root);

            mainSplitPane.getItems().remove(currentPane);
            mainSplitPane.getItems().add(newPane);
            currentPane = newPane;

        } catch (IOException e) {
            e.printStackTrace();

        }
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
        try {

            Parent root = FXMLLoader.load(getClass().getResource("bursat.fxml"));

            AnchorPane newPane = new AnchorPane(root);

            mainSplitPane.getItems().remove(currentPane);
            mainSplitPane.getItems().add(newPane);
            currentPane = newPane;


        } catch (IOException e) {
            e.printStackTrace();

        }
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
