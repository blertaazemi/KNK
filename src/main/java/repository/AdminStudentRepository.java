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

    public static List<AdminStudent> getStudents(Connection connection) throws SQLException {
        List<AdminStudent> studentList = new ArrayList<>();
        String sql = "Select * from tbl_students";
        PreparedStatement statement = connection.prepareStatement(sql);
        ResultSet resultSet = statement.executeQuery();
        while (resultSet.next()) {
            int id = resultSet.getInt("Id");
            String first_name = resultSet.getString("first_name");
            String last_name = resultSet.getString("last_name");
            String username = resultSet.getString("username");
            String email = resultSet.getString("email");
            String password = resultSet.getString("password");

            AdminStudent studentModel = new AdminStudent(id, first_name, last_name, username, email, password);
            studentList.add(studentModel);
        }
        resultSet.close();
        statement.close();
        return studentList;

    }
}


