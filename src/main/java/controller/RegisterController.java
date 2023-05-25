package controller;

import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import models.dto.CreateStudentDto;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.PasswordField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import models.Student;
import repository.StudentRepository;
import service.ConnectionUtil;
import service.PasswordHasher;


import java.io.IOException;
import java.net.URL;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Locale;
import java.util.ResourceBundle;

public class RegisterController implements Initializable {
    @FXML
    private AnchorPane signup_form;
    @FXML
    private AnchorPane login_form;
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
    private Button krijoLlogariNeseSki;

    @FXML
    private ImageView logoImageView;

    private StudentRepository studentRepository;

    @FXML
    private Label emriLabel;
    @FXML
    private Label fjalekalimiLabel;

    @FXML
    private Label konfirmofjalekaliminLabel;
    @FXML
    private Label mbiemriLabel;
    @FXML
    private Label perdoruesiLabel;

    @FXML
    private Label regjistrohuLabel;
    @FXML
    private Button test;

    public RegisterController() {
        this.studentRepository = new StudentRepository();
    }
    private ResourceBundle bundle;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Locale locale = Locale.getDefault();
        ResourceBundle translate = ResourceBundle.getBundle("translations.content", locale);
    }

    private void translateElements() {
        emriLabel.setText(bundle.getString("emriLabel"));
        mbiemriLabel.setText(bundle.getString("mbiemriLabel"));
        perdoruesiLabel.setText(bundle.getString("perdoruesiLabel"));
        fjalekalimiLabel.setText(bundle.getString("fjalekalimiLabel"));
        konfirmofjalekaliminLabel.setText(bundle.getString("konfirmofjalekaliminLabel"));
        registerButton.setText(bundle.getString("registerButton"));
        mbyllButton.setText(bundle.getString("mbyllButton"));
        test.setText(bundle.getString("test"));
        regjistrohuLabel.setText(bundle.getString("regjistrohuLabel"));

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


    public void register(ActionEvent event) {
        alertMessage alert = new alertMessage();
        try {
            if (emriTextField.getText().isEmpty() || mbiemriTextField.getText().isEmpty() ||
                    perdoruesiTextField.getText().isEmpty() || emailTextField.getText().isEmpty() ||
                    setPasswordField.getText().isEmpty() || confirmPasswordField.getText().isEmpty()) {
                alert.errorMessage("Duhet ti plotesoni te gjitha fushat!!");
                return;
            }else if (!setPasswordField.getText().equals(confirmPasswordField.getText())) {
                alert.errorMessage("Fjalekalimet nuk perputhen. Provoni perseri.");
            } else if (setPasswordField.getLength() < 8) {
                alert.errorMessage("Gjatesia e fjalekalimit duhet te jete te pakten 8 karaktera!");
            }

            Connection connection = ConnectionUtil.getConnection();
            if (connection != null) {
            boolean usernameExists = studentRepository.checkUsernameExists(perdoruesiTextField.getText(), connection);
            boolean emailExists = studentRepository.checkEmailExists(emailTextField.getText(), connection);

            if (usernameExists) {
                alert.errorMessage("Perdoruesi ekziston. Provoni nje perdorues tjeter.");
                return;
            }

            if (emailExists) {
                alert.errorMessage("Emaili ekziston. Provoni nje email tjeter.");
                return;
            }


                CreateStudentDto StudentDto = new CreateStudentDto(emriTextField.getText(), mbiemriTextField.getText(), perdoruesiTextField.getText(),
                        emailTextField.getText() ,setPasswordField.getText(),PasswordHasher.generateSalt());
                StudentRepository studentRepository = new StudentRepository();
                studentRepository.insert(StudentDto, connection);
                try {
                    FXMLLoader fxmlLoader = new FXMLLoader(Login.class.getResource("Login.fxml"));
                    Pane pane = fxmlLoader.load();
                    Scene scene = new Scene(pane);
                    Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                    stage.setScene(scene);
                    stage.show();
                } catch (IOException e) {
                    System.err.println("Error loading FXML file: " + e.getMessage());
                }
            } else {
                System.out.println("Failed to insert user in the database");
            }
        } catch (SQLException e) {
            System.err.println("Error inserting user into database: " + e.getMessage());
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }


    public void registerClearFields() {
        emriTextField.setText("");
        mbiemriTextField.setText("");
        perdoruesiTextField.setText("");
        emailTextField.setText("");
        setPasswordField.setText("");
        confirmPasswordField.setText("");
    }

    @FXML
    public void switchForm(ActionEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Login.class.getResource("login.fxml"));
            Parent parent = fxmlLoader.load();
            Scene scene = new Scene(parent);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            System.err.println("Error loading FXML file: " + e.getMessage());
        }
    }

    public void cancelButtonOnAction(ActionEvent event){
        Stage stage = (Stage) mbyllButton.getScene().getWindow();
        stage.close();
    }




    public void setFormData(String firstName, String lastName, String email) {

    }

}

