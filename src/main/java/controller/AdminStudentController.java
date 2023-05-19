package controller;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
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

    public void initialize(URL location, ResourceBundle resources) {
        col_studentId.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().id).asObject());
        col_firstName.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().first_name));
        col_lastName.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().last_name));
        col_username.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().username));
        col_email.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().email));
        col_password.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().password));

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
