package repository;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableView;
import models.AdminStudent;
import service.ConnectionUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AdminStudentRepository {
    public static List<AdminStudent> populateStudentsTable(TableView<AdminStudent> tableView) throws SQLException {
        List<AdminStudent> students = new ArrayList<>();
        String sql = "SELECT * FROM tbl_students";
        Connection connection = ConnectionUtil.getConnection();
        PreparedStatement statement = connection.prepareStatement(sql);
        ResultSet resultSet = statement.executeQuery();

        while (resultSet.next()) {
            int id = resultSet.getInt("id");
            String firstname = resultSet.getString("first_name");
            String lastname = resultSet.getString("last_name");
            String username = resultSet.getString("username");
            String email = resultSet.getString("email");
            String password = resultSet.getString("password");

            AdminStudent student = new AdminStudent(id, firstname, lastname, username, email, password);
            students.add(student);
        }

        // Set the data in the TableView
        ObservableList<AdminStudent> studentList = FXCollections.observableArrayList(students);
        tableView.setItems(studentList);

        // Close the resources
        resultSet.close();
        statement.close();
        connection.close();

        return students;
    }

}
