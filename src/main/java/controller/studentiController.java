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
import java.util.ResourceBundle;

import static service.ConnectionUtil.getConnection;

import service.PasswordHasher;
import java.util.Base64;

public class studentiController {
    @FXML
    private AnchorPane studentPane;
    @FXML
    private Label emriLabel;
    @FXML
    private Label mbiemriLabel;
    @FXML
    private Label usernameLabel;
    @FXML
    private Label emailLabel;
    @FXML
    private Button applyButton;

    public void initializeStudentData(String username) {
        try {
            Connection connection = getConnection();
            Statement statement = connection.createStatement();
            String query = "SELECT * FROM tbl_students WHERE username = '" + username + "'";
            ResultSet resultSet = statement.executeQuery(query);

            if (resultSet.next()) {
                String emri = resultSet.getString("first_name");
                String mbiemri = resultSet.getString("last_name");
                String email = resultSet.getString("email");

                emriLabel.setText(emri);
                mbiemriLabel.setText(mbiemri);
                usernameLabel.setText(username);
                emailLabel.setText(email);
            }

            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private Connection getConnection() throws SQLException {
        String url = "jdbc:mysql://localhost/projekti_knk";
        String username = "your_username";
        String password = "your_password";
        return DriverManager.getConnection(url, username, password);
    }

    @FXML
    private void applyForScholarship() {
        // Code to handle the "Apply for Scholarship" button click event
        // You can add your logic here


    }

    @FXML
    private void navigateToAplikimi(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Aplikimi.class.getResource("Aplikimi.fxml"));
        Pane pane = fxmlLoader.load();
        ScrollPane scrollPane = new ScrollPane(pane);
        scrollPane.setFitToWidth(true);
        scrollPane.setFitToHeight(true);

        Scene scene = new Scene(scrollPane, 1400, 600);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }


}