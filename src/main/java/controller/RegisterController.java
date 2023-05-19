package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.PasswordField;
import javafx.scene.image.ImageView;
import service.ConnectionUtil;

import java.io.File;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

import static service.ConnectionUtil.getConnection;

import service.PasswordHasher;
import java.util.Base64;

public class RegisterController implements Initializable {
    @FXML
    private TextField emriTextField;
    @FXML
    private TextField mbiemriTextField;
    @FXML
    private TextField perdoruesiTextField;
    @FXML
    private TextField emailTextField;
    @FXML
    private PasswordField setPasswordField;
    @FXML
    private PasswordField confirmPasswordField;
    @FXML
    private Button registerButton;
    @FXML
    private Button mbyllButton;
    @FXML
    private ImageView logoImageView;
    @FXML
    //private Label errorRegister; //me hek edhe ne scene builder

    private PreparedStatement prepareStatement;
    private ResultSet resultSet;
    private Statement statement;
    private Connection connection;

    public void register(ActionEvent event)throws SQLException {

        alertMessage alert = new alertMessage();
        //kontrollojme nese fushat jane te zbrazeta
        try {
            if (emriTextField.getText().isEmpty() || mbiemriTextField.getText().isEmpty() || emailTextField.getText().isEmpty() || perdoruesiTextField.getText().isEmpty() || setPasswordField.getText().isEmpty() || confirmPasswordField.getText().isEmpty()) {
                alert.errorMessage("Duhet ti plotesoni te gjitha fushat!!");
                return;
        }else if(setPasswordField.getText()==confirmPasswordField.getText()){         //a eshte passwordi konfirmues i sakte
                alert.errorMessage("Fjalekalimet nuk perputhen. Provoni perseri.");

            }else if(setPasswordField.getLength()<8){                  //gjatesia e passwordit me a madhe se 8
                alert.errorMessage("Gjatesia e fjalekalimit duhet te jete te pakten 8 karaktera!");

            }else{
                String checkUsername = "SELECT * FROM tbl_students WHERE username = '"+perdoruesiTextField.getText()+"'";
                connection = getConnection();
                statement = connection.createStatement();
                resultSet = statement.executeQuery(checkUsername);

                if(resultSet.next()){
                    alert.errorMessage("Perdoruesi "+perdoruesiTextField.getText() + "nuk eshte i lire per perdorim.");
                }else{
                    String salt = PasswordHasher.generateSalt();
                    String saltedHash = PasswordHasher.generateSaltedHash(setPasswordField.getText(),salt);

                    String insertData = "INSERT INTO tbl_students "
                            + "(first_name, last_name, username, email, password, salt)"
                            + "VALUES(?, ?, ?, ?, ?, ?)";
                    prepareStatement = connection.prepareStatement(insertData);
                    prepareStatement.setString(1,emriTextField.getText());
                    prepareStatement.setString(2,mbiemriTextField.getText());
                    prepareStatement.setString(3,perdoruesiTextField.getText());
                    prepareStatement.setString(4,emailTextField.getText());
                    prepareStatement.setString(5, saltedHash);
                    prepareStatement.setString(6, Base64.getEncoder().encodeToString(salt.getBytes()));


                    prepareStatement.executeUpdate();

                    alert.successMessage("Jeni regjistruar me sukses!!");   //me bo qe me direktu te home page

                    registerClearFields();

                }

            }

        }catch(SQLException e){
            e.printStackTrace();

        }
    }

    //fshihen fushat pas regjistrimit
    public void registerClearFields(){
        emriTextField.setText("");
        mbiemriTextField.setText("");
        perdoruesiTextField.setText("");
        emailTextField.setText("");
        setPasswordField.setText("");
        confirmPasswordField.setText("");
    }

    public void switchForm(ActionEvent event){
        if(event.getSource() == )

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

}
