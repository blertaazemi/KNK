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
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import models.AdminLoginModel;
import repository.AdminLoginRepository;
import service.ConnectionUtil;

import java.io.IOException;
import java.net.URL;
import java.security.NoSuchAlgorithmException;
import java.sql.*;
import java.util.Locale;
import java.util.ResourceBundle;

public class AdminLogin implements Initializable {

    @FXML
    private Label adminfjalekalimiLabel;

    @FXML
    private Button adminkthehuButton;

    @FXML
    private Button adminloginButton;

    @FXML
    private PasswordField adminpassword;

    @FXML
    private TextField adminusername;

    @FXML
    private Label adminusernameLabel;

    private ResourceBundle bundle;
    private Connection connection;

    private PreparedStatement prepareStatement;
    private ResultSet resultSet;
    private Statement statement;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        Locale locale = Locale.getDefault();
        ResourceBundle translate = ResourceBundle.getBundle("translations.content", locale);

        try {
            connection = ConnectionUtil.getConnection(); // Initialize the connection
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void adminloginButtonOnAction(ActionEvent event) throws SQLException, IOException {
        alertMessage alertMessage = new alertMessage();
        if (!adminusername.getText().isBlank() && !adminpassword.getText().isBlank()) {
            login(event);
        } else {
            alertMessage.errorMessage("Ju lutem shkruani emrin e përdoruesit dhe fjalëkalimin");
        }
    }

    private void translateElements() {
        adminusernameLabel.setText(bundle.getString("adminusernameLabel"));
        adminfjalekalimiLabel.setText(bundle.getString("adminfjalekalimiLabel"));
        adminloginButton.setText(bundle.getString("adminloginButton"));
        adminkthehuButton.setText(bundle.getString("adminkthehuButton"));
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
    public void adminkthehuButtonOnAction(ActionEvent event) throws IOException {
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


    void login(ActionEvent event) {
        try {
            //Connection  connection = ConnectionUtil.getConnection();
            if (connection != null) {
                AdminLoginModel loginModel = new AdminLoginModel(adminusername.getText(), adminpassword.getText());
                AdminLoginRepository adminloginRepository = new AdminLoginRepository();
                boolean validlogin = adminloginRepository.login(loginModel, connection);
                if (validlogin) {
                    try {
                        FXMLLoader fxmlLoader = new FXMLLoader(AdminDashboard.class.getResource("admin.fxml"));
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
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }catch(NoSuchAlgorithmException e) {  // try block per qit catch ku e ki
                throw new RuntimeException(e);
        }
    }

}

    //qitu translate elements mi qit


//    public void translateEn(ActionEvent event){
//        Locale.setDefault(new Locale("en"));
//        bundle = ResourceBundle.getBundle("translations.content", Locale.getDefault());
//        this.translateElements();
//    }
//
//    public void translateAl(ActionEvent event){
//        Locale.setDefault(new Locale("sq"));
//        bundle = ResourceBundle.getBundle("translations.content", Locale.getDefault());
//        this.translateElements();
//    }



