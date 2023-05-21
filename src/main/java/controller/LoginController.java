package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import models.AdminLogin;
import models.Login;
import repository.LoginRepository;
import service.ConnectionUtil;

import java.io.IOException;
import java.net.URL;
import java.security.NoSuchAlgorithmException;
import java.sql.*;
import java.util.ResourceBundle;

public class LoginController implements Initializable {
    @FXML
    private TextField perdoruesiTextField;
    @FXML
    private PasswordField enterPasswordField;

    @FXML
    private Button cancelButton;
    @FXML
    private Button loginButton;

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


    public void loginButtonOnAction(ActionEvent event) throws SQLException, IOException {
        loginMessageLabel.setText("Provoni te kyceni");
        if (!perdoruesiTextField.getText().isBlank() && !enterPasswordField.getText().isBlank()) {
            validateLogin(event);
        } else {
            loginMessageLabel.setText("Ju lutem shkruani emrin e përdoruesit dhe fjalëkalimin");
        }
    }

    public void cancelButtonOnAction(ActionEvent event) {
        Stage stage = (Stage) cancelButton.getScene().getWindow();
        stage.close();
    }

    public void validateLogin(ActionEvent event) throws SQLException, IOException {
        alertMessage alertMessage = new alertMessage();
        try {
            Connection connection = ConnectionUtil.getConnection();

            if (connection != null) {

                Login loginModel = new Login(perdoruesiTextField.getText(), enterPasswordField.getText());
                LoginRepository loginRepository = new LoginRepository();
                boolean validLogin = loginRepository.login(loginModel, connection);

                System.out.println("validLogin: " + validLogin); // po del false dmth sosht tu bo logini :(

                if (validLogin) {
                    // Redirect to student dashboard
                    try {
                        FXMLLoader fxmlLoader = new FXMLLoader(Register.class.getResource("register.fxml"));
                        Pane pane = fxmlLoader.load();
                        Scene scene = new Scene(pane);
                        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                        stage.setScene(scene);
                        stage.show();
                    } catch (IOException e) {
                        alertMessage.errorMessage("Failed to load student dashboard");
                    }

                } else {
                    alertMessage.errorMessage("Përdoruesi ose fjalëkalimi i gabuar!");
                }

            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());

        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException();
        }
    }




}
