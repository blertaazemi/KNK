package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

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

    public void initialize(URL url, ResourceBundle resourceBundle) {
        Image bookImage = new Image(getClass().getResourceAsStream("/Images/book.png"));
        img1.setImage(bookImage);}
    

    public void homebtnClick(ActionEvent actionEvent) {
    }

    public void studentsbtnClick(ActionEvent actionEvent) {
    }

    public void bursatbtnClick(ActionEvent actionEvent) {
    }

    public void aplbtnClick(ActionEvent actionEvent) {
    }

    public void logBtnClick(ActionEvent actionEvent) {
    }
}
