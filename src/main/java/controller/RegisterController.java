package controller;

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
import service.PasswordHasher;


import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
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
    private Button kycuNeseKiLlogari;
    @FXML
    private Button krijoLlogariNeseSki;

    @FXML
    private ImageView logoImageView;

    private StudentRepository studentRepository;

    public RegisterController() {
        this.studentRepository = new StudentRepository();
    }

    public void register(ActionEvent event) throws SQLException {
        alertMessage alert = new alertMessage();
        try {
            if (emriTextField.getText().isEmpty() || mbiemriTextField.getText().isEmpty() ||
                    emailTextField.getText().isEmpty() || perdoruesiTextField.getText().isEmpty() ||
                    setPasswordField.getText().isEmpty() || confirmPasswordField.getText().isEmpty()) {
                alert.errorMessage("Duhet ti plotesoni te gjitha fushat!!");
                return;
            } else if (!setPasswordField.getText().equals(confirmPasswordField.getText())) {
                alert.errorMessage("Fjalekalimet nuk perputhen. Provoni perseri.");
            } else if (setPasswordField.getLength() < 8) {
                alert.errorMessage("Gjatesia e fjalekalimit duhet te jete te pakten 8 karaktera!");
            } else {

                String first_name = emriTextField.getText();
                String last_name = mbiemriTextField.getText();
                String username = perdoruesiTextField.getText();
                String email = emailTextField.getText();
                String password = setPasswordField.getText();
                String confirm = confirmPasswordField.getText();
                String salt = PasswordHasher.generateSalt();
                String saltedHash = PasswordHasher.generateSaltedHash(password, salt);

                CreateStudentDto createStudentDto = new CreateStudentDto(first_name, last_name, username, email, saltedHash, salt);

                Student student = new Student(first_name, last_name, username, email, saltedHash, salt);



                studentRepository.createStudent(student);

                alert.successMessage("Jeni regjistruar me sukses!!");
                registerClearFields();
            }
        } catch (SQLException e) {
            e.printStackTrace();
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

    public void switchForm(ActionEvent event) {
        if (event.getSource() == kycuNeseKiLlogari) {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/controller/login.fxml"));
                Parent loginForm = loader.load();
                LoginController loginController = loader.getController();

                // Perform any necessary operations or pass data to the LoginController

                signup_form.getScene().setRoot(loginForm);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else if (event.getSource() == krijoLlogariNeseSki) {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/controller/signup.fxml"));
                Parent signupForm = loader.load();
                RegisterController registerController = loader.getController();

                // Perform any necessary operations or pass data to the RegisterController

                login_form.getScene().setRoot(signupForm);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void cancelButtonOnAction(ActionEvent event){
        Stage stage = (Stage) mbyllButton.getScene().getWindow();
        stage.close();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }


    public void setFormData(String firstName, String lastName, String email) {

    }

}

