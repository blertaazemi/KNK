package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import service.ConnectionUtil;

import java.io.File;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

public class LogInController implements Initializable{
    @FXML
    private TextField perdoruesiTextField;
    @FXML
    private PasswordField enterPasswordField;

    @FXML
    private Button cancelButton;

    @FXML
    private Label loginMessageLabel;

    @FXML
    private ImageView bookImageView;

    @FXML
    private ImageView userImageView;

    private Connection connection;

    private PreparedStatement prepareStatement;
    private ResultSet resultSet;
    private Statement statement;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Image bookImage = new Image(getClass().getResourceAsStream("/Images/book.png"));
        bookImageView.setImage(bookImage);

        Image userImage = new Image(getClass().getResourceAsStream("/Images/user.png"));
        userImageView.setImage(userImage);

        try {
            connection = ConnectionUtil.getConnection(); // Initialize the connection
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public void loginButtonOnAction(ActionEvent event) throws SQLException {
        loginMessageLabel.setText("Provoni te kyceni");
        if(perdoruesiTextField.getText().isBlank()==false && enterPasswordField.getText().isBlank()==false){
            validateLogin();
        }else{
            loginMessageLabel.setText("Ju lutem shkruani emrin e përdoruesit dhe fjalëkalimin");
        }

    }

    public void cancelButtonOnAction(ActionEvent event){
        Stage stage = (Stage) cancelButton.getScene().getWindow();
        stage.close();
    }

    public void validateLogin() throws SQLException {
            String username = perdoruesiTextField.getText();
            String password = enterPasswordField.getText();

            String sql = "SELECT * FROM users WHERE username = ? AND password = ?";
            PreparedStatement statement = connection.prepareStatement(sql);        //
            statement.setString(1, username);
            statement.setString(2, password);

            ResultSet resultSet = statement.executeQuery();                       //

            if (resultSet.next()) {
                loginMessageLabel.setText("Jeni kyçur me sukses!");
                // Perform any other actions you need after successful login
            } else {
                loginMessageLabel.setText("Përdoruesi ose fjalëkalimi i gabuar!");
            }

            statement.close();
            resultSet.close();
        }



    }
//    @FXML
//    private void loginClick(ActionEvent e){
//        String username = this.txtUsername.getText();
//        String password = this.pwdPassword.getText();
//
//        try{
//            User user = UserAuthService.login(username,password);
//            if(user==null){
//                //mesazh
//                System.out.println("Username or password is incorrect! ");
//                return;
//            }
//            System.out.println("User is correct!");
//        }catch(SQLException sqlException){
//            System.out.println("Gabim ne baze te te dhenave!!");
//        }
//
//        //System.out.printf("Username: %s, Password: %s", username, password);
//    }
//    @FXML
//    private void cancelClick(ActionEvent e){
//        this.txtUsername.setText("");
//        this.pwdPassword.setText("");
//    }
//
//
//    @Override
//    public void initialize(URL url, ResourceBundle resourceBundle) {
//        System.out.println("Initialize");
//
//        Locale locale=Locale.getDefault();
//        ResourceBundle translate=ResourceBundle.getBundle("translations.content",locale);
//        translate.getString("Login.button.text");
//        this.btnLogin.setText(translate.getString("Login.button.text"));
//    }
