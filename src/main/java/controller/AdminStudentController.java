package controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import models.AdminStudent;
import repository.AdminStudentRepository;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class AdminStudentController implements Initializable {
    @FXML
    private TableColumn<AdminStudent, Integer> col_studentId;

    @FXML
    private TableColumn<AdminStudent, String> col_firstName;

    @FXML
    private TableColumn<AdminStudent, String> col_lastName;

    @FXML
    private TableColumn<AdminStudent, String> col_username;

    @FXML
    private TableColumn<AdminStudent, String> col_email;

    @FXML
    private TableColumn<AdminStudent, String> col_password;


    @FXML
    private TableView<AdminStudent> studentTableView;

    public void initialize(URL location, ResourceBundle resources) {
        try {
            AdminStudentRepository.populateStudentsTable(studentTableView);
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle the exception as needed
        }

    }
}
