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
import models.dto.CreateAdminDto;
import repository.AdminRepository;
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

public class AdminRegister implements Initializable {

    @FXML
    private Label admincpassLabel;

    @FXML
    private Label adminemailLabel;

    @FXML
    private Label adminemriLabel;

    @FXML
    private Label adminmbiemriLabel;

    @FXML
    private Label adminpassLabel;

    @FXML
    private Button adminregButton;

    @FXML
    private PasswordField adminregcpass;

    @FXML
    private TextField adminregemail;

    @FXML
    private TextField adminregemri;

    @FXML
    private TextField adminregmbiemri;

    @FXML
    private PasswordField adminregpass;

    @FXML
    private TextField adminregusername;

    @FXML
    private Label adminusernameLabel;

    private AdminRepository adminRepository;

    public AdminRegister(){
        this.adminRepository= new AdminRepository();
    }

    private ResourceBundle bundle;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Locale locale = Locale.getDefault();
        ResourceBundle translate = ResourceBundle.getBundle("translations.content", locale);
    }

    public void register(ActionEvent event) {
        alertMessage alert = new alertMessage();
        try {
            if (adminregemri.getText().isEmpty() || adminregmbiemri.getText().isEmpty() ||
                    adminregusername.getText().isEmpty() || adminregemail.getText().isEmpty() ||
                    adminregpass.getText().isEmpty() || adminregcpass.getText().isEmpty()) {
                alert.errorMessage("Duhet ti plotesoni te gjitha fushat!!");
                return;
            }else if (!adminregpass.getText().equals(adminregcpass.getText())) {
                alert.errorMessage("Fjalekalimet nuk perputhen. Provoni perseri.");
            } else if (adminregpass.getLength() < 8) {
                alert.errorMessage("Gjatesia e fjalekalimit duhet te jete te pakten 8 karaktera!");
            }

            Connection connection = ConnectionUtil.getConnection();
            if (connection != null) {
                boolean usernameExists = adminRepository.checkUsernameExists(adminregusername.getText(), connection);
                boolean emailExists = adminRepository.checkEmailExists(adminregemail.getText(), connection);

                if (usernameExists) {
                    alert.errorMessage("Perdoruesi ekziston. Provoni nje perdorues tjeter.");
                    return;
                }

                if (emailExists) {
                    alert.errorMessage("Emaili ekziston. Provoni nje email tjeter.");
                    return;
                }


                CreateAdminDto AdminDto = new CreateAdminDto(adminregemri.getText(), adminregmbiemri.getText(), adminregusername.getText(),
                        adminregemail.getText() ,adminregcpass.getText(), PasswordHasher.generateSalt());
                AdminRepository adminRepository = new AdminRepository();
                adminRepository.insert(AdminDto, connection);
                try {
                    FXMLLoader fxmlLoader = new FXMLLoader(Login.class.getResource("adminlogin.fxml"));
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

}
