package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import models.User;
import service.UserAuthService;

import java.net.URL;
import java.sql.SQLException;
import java.util.Locale;
import java.util.ResourceBundle;

public class LogInController  implements Initializable {
    @FXML
    private TextField txtUsername;
    @FXML
    private PasswordField pwdPassword;

    @FXML
    private Button btnLogin;


    @FXML
    private void loginClick(ActionEvent e){
        String username = this.txtUsername.getText();
        String password = this.pwdPassword.getText();

        try{
            User user = UserAuthService.login(username,password);
            if(user==null){
                //mesazh
                System.out.println("Username or password is incorrect! ");
                return;
            }
            System.out.println("User is correct!");
        }catch(SQLException sqlException){
            System.out.println("Gabim ne baze te te dhenave!!");
        }

        //System.out.printf("Username: %s, Password: %s", username, password);
    }
    @FXML
    private void cancelClick(ActionEvent e){
        this.txtUsername.setText("");
        this.pwdPassword.setText("");
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        System.out.println("Initialize");

        Locale locale=Locale.getDefault();
        ResourceBundle translate=ResourceBundle.getBundle("translations.content",locale);
        translate.getString("Login.button.text");
        this.btnLogin.setText(translate.getString("Login.button.text"));
    }
}