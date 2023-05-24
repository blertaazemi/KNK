package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import models.Login;
import repository.LoginRepository;
import service.ConnectionUtil;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;


import java.io.IOException;
import java.net.URL;
import java.security.NoSuchAlgorithmException;
import java.sql.*;
import java.util.Locale;
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
    @FXML
    private Label fjalekalimiLabel;

    @FXML
    private Button krijoLlogariNeseSki;

    @FXML
    private Label kycuLabel;

    @FXML
    private Label perdoruesiLabel;

    @FXML
    private Button loginkthehu;

    private ResourceBundle bundle;
    private Connection connection;

    private PreparedStatement prepareStatement;
    private ResultSet resultSet;
    private Statement statement;
    @FXML
    private BorderPane login_form;

    @FXML
    private Button kthehuStudent;

    @FXML
    private Button translateAL;
    @FXML
    private Button translateEN;
    @FXML
    private Label loginMessage;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        login_form.addEventFilter(KeyEvent.KEY_PRESSED, event -> {
            if (event.getCode() == KeyCode.ENTER) {
                loginButton.fire();
            }
        });

        Image bookImage = new Image(getClass().getResourceAsStream("/Images/book.png"));
        bookImageView.setImage(bookImage);

        Image userImage = new Image(getClass().getResourceAsStream("/Images/user.png"));
        userImageView.setImage(userImage);

        Locale locale = Locale.getDefault();
        ResourceBundle translate = ResourceBundle.getBundle("translations.content", locale);

        try {
            connection = ConnectionUtil.getConnection(); // Initialize the connection
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void translateElements() {
        perdoruesiLabel.setText(bundle.getString("perdoruesiLabel"));
        fjalekalimiLabel.setText(bundle.getString("fjalekalimiLabel"));
        loginButton.setText(bundle.getString("loginButton"));
        cancelButton.setText(bundle.getString("cancelButton"));
        loginMessageLabel.setText(bundle.getString("loginMessageLabel"));
        kycuLabel.setText(bundle.getString("kycuLabel"));
        krijoLlogariNeseSki.setText(bundle.getString("krijoLlogariNeseSki"));
        kthehuStudent.setText(bundle.getString("kthehuStudent"));
        loginMessage.setText(bundle.getString("loginMessage"));

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


    @FXML
    public void loginButtonOnAction(ActionEvent event) throws SQLException, IOException {
        if (!perdoruesiTextField.getText().isBlank() && !enterPasswordField.getText().isBlank()) {
            login(event);
        } else {
            loginMessageLabel.setText("Ju lutem shkruani emrin e përdoruesit dhe fjalëkalimin");
        }
    }

    public void cancelButtonOnAction(ActionEvent event) {
        Stage stage = (Stage) cancelButton.getScene().getWindow();
        stage.close();
    }

    void login(ActionEvent event) {
        try {
            //Connection  connection = ConnectionUtil.getConnection();
            if (connection != null) {
                Login loginModel = new Login(perdoruesiTextField.getText(), enterPasswordField.getText());
                LoginRepository loginRepository = new LoginRepository();
                boolean validlogin = loginRepository.login(loginModel, connection);
                if(validlogin) {
                    try {
                        FXMLLoader fxmlLoader = new FXMLLoader(Studenti.class.getResource("studenti.fxml"));
                        Pane pane = fxmlLoader.load();
                        Scene scene = new Scene(pane);
                        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                        stage.setScene(scene);
                        stage.show();

                    } catch (IOException e) {
                        System.err.println("Error loading FXML file: " + e.getMessage());
                    }

                }
            }
        }
        catch (SQLException e) {
            System.err.println(e.getMessage());

        } catch (NoSuchAlgorithmException e) {  // try block per qit catch ku e ki
            throw new RuntimeException(e);
        }
    }

    @FXML
    public void regjistrohu(ActionEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(controller.Register.class.getResource("register.fxml"));
            Parent parent = fxmlLoader.load();
            Scene scene = new Scene(parent);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            System.err.println("Error loading FXML file: " + e.getMessage());
        }
    }

    @FXML
    public void loginkthehu(ActionEvent event) throws IOException {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(ButtonsLogin.class.getResource("buttonslogin.fxml"));
            Pane pane = fxmlLoader.load();
            Scene scene = new Scene(pane);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            System.err.println("Error loading FXML file: " + e.getMessage());
        }
    }


}