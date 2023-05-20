package controller;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import models.AdminStudent;
import repository.AdminStudentRepository;
import service.ConnectionUtil;

import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;

public class AdminStudentController implements Initializable {
    @FXML
    public TableColumn<AdminStudent, Integer> col_studentId;

    @FXML
    public TableColumn<AdminStudent, String> col_firstName;

    @FXML
    public TableColumn<AdminStudent, String> col_lastName;

    @FXML
    public TableColumn<AdminStudent, String> col_username;

    @FXML
    public TableColumn<AdminStudent, String> col_email;

    @FXML
    public TableColumn<AdminStudent, String> col_password;


    @FXML
    private TableView<AdminStudent> studentTableView;
    @FXML
    private TextField StudentIdField;
    @FXML
    private TextField FirstNameField;
    @FXML
    private TextField LastNameField;

    @FXML
    private TextField UsernameField;

    @FXML
    private TextField EmailField;





    public void SelectStudent() {
        AdminStudent student = studentTableView.getSelectionModel().getSelectedItem();
        int num = studentTableView.getSelectionModel().getSelectedIndex();

        if ((num - 1) < -1) {
            return;
        }
       StudentIdField.setText(String.valueOf(student.getId()));
        FirstNameField.setText(student.getFirst_name());
        LastNameField.setText(student.getLast_name());
        UsernameField.setText(student.getUsername());
        EmailField.setText(student.getEmail());



    }

    public void initialize(URL location, ResourceBundle resources) {
        col_studentId.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getId()).asObject());
        col_firstName.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getFirst_name()));
        col_lastName.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getLast_name()));
        col_username.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getUsername()));
        col_email.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getEmail()));
        col_password.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getPassword()));

        List<AdminStudent> studentModelList = null;
        Connection connection = null;
        try {
            connection = ConnectionUtil.getConnection();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        try {
            studentModelList = AdminStudentRepository.getStudents(connection);

        } catch (SQLException e) {
            e.printStackTrace();
            // Handle the exception as needed
        }

        ObservableList<AdminStudent> studentObservableList = FXCollections.observableList(studentModelList);
        studentTableView.setItems(studentObservableList);

    }



}


