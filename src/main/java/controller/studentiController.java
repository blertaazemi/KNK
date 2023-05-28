package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import service.ConnectionUtil;
import java.io.IOException;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.Locale;
import java.util.ResourceBundle;

import static service.ConnectionUtil.getConnection;

import service.PasswordHasher;
import java.util.Base64;

public class studentiController {
    @FXML
    private AnchorPane studentPane;

    //ku shkruhen te dhenat e studentit:
    @FXML
    private Label emriid1;
    @FXML
    private Label mbiemriid1;
    @FXML
    private Label usernameid1;
    @FXML
    private Label emailid1;
    @FXML
    private Label emriid11;
    @FXML
    private Label mbiemriid11;
    @FXML
    private Label usernameid11;
    @FXML
    private Label emailid11;

  @FXML
  private Label idid1;

  @FXML
  private Label idid11;

    @FXML
    private Button studentibtn;

    @FXML
    private Button applyButton;
    private ResourceBundle bundle;

    @FXML
    private Button translateAL;
    @FXML
    private Button translateEN;
    @FXML
    private Button logoutStudenti;

    private Connection connection;



    private void translateElements() {

        Locale locale = Locale.getDefault();
        ResourceBundle translate = ResourceBundle.getBundle("translations.content", locale);

        emriid11.setText(bundle.getString("emriid11"));
        mbiemriid11.setText(bundle.getString("mbiemriid11"));
        usernameid11.setText(bundle.getString("usernameid11"));
        emailid11.setText(bundle.getString("emailid11"));
//        emritxt.setText(bundle.getString("emritxt"));
//        mbiemritxt.setText(bundle.getString("mbiemritxt"));
//        usernametxt.setText(bundle.getString("usernametxt"));
//        emailtxt.setText(bundle.getString("emailtxt"));
        studentibtn.setText(bundle.getString("studentibtn"));
        logoutStudenti.setText(bundle.getString("logoutStudenti"));

    }


    public void translateEn(ActionEvent event) {
        Locale.setDefault(new Locale("en"));
        bundle = ResourceBundle.getBundle("translations.content", Locale.getDefault());
        this.translateElements();
    }

    public void translateAl(ActionEvent event) {
        Locale.setDefault(new Locale("sq"));
        bundle = ResourceBundle.getBundle("translations.content", Locale.getDefault());
        this.translateElements();
    }
    public void initializeStudentData(String username) {
        try {
            Connection connection = getConnection();
            Statement statement = connection.createStatement();
            String query = "SELECT * FROM tbl_students WHERE username = '" + username + "'";
            ResultSet resultSet = statement.executeQuery(query);

            if (resultSet.next()) {
                String id = resultSet.getString("id");
                String emri = resultSet.getString("first_name");
                String mbiemri = resultSet.getString("last_name");
                String email = resultSet.getString("email");

                idid1.setText(id);
                emriid1.setText(emri);
                mbiemriid1.setText(mbiemri);
                usernameid1.setText(username);
                emailid1.setText(email);
            }

            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


//    private Connection getConnection() throws SQLException {
//        String url = "jdbc:mysql://localhost/projekti_knk";
//        String username = "your_username";
//        String password = "your_password";
//        return DriverManager.getConnection(url, username, password);
//    }

    @FXML
    private void applyForScholarship() {
        // Code to handle the "Apply for Scholarship" button click event
        // You can add your logic here


    }

    @FXML
    private void navigateToAplikimi(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Aplikimi.class.getResource("Aplikimi.fxml"));
        Parent aplikimiPane = fxmlLoader.load();
        AplikimiController aplikimiController = fxmlLoader.getController();

        // Pass the student data to the AplikimiController
        aplikimiController.setStudentData(emriid1.getText(), mbiemriid1.getText(), usernameid1.getText(), emailid1.getText());

        Scene scene = new Scene(aplikimiPane, 1400, 600);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    public void handleLogout(ActionEvent event) {
        // Load the login.fxml file
        Parent root;
        try {
            root = FXMLLoader.load(getClass().getResource("login.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }

        // Get the current stage
        Stage stage = (Stage) logoutStudenti.getScene().getWindow();

        // Set the login page as the new root of the stage
        stage.setScene(new Scene(root));

        // Show the updated stage
        stage.show();
    }




}