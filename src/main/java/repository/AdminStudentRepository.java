package repository;

import models.AdminStudent;
import models.dto.CreateStudentDto;
import models.dto.UpdateStudentDto;
import service.ConnectionUtil;
import service.PasswordHasher;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AdminStudentRepository {
    //marrja e te dhenave nga databaza
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
  // vendosja e nje studenti te ri ne databaze(add)
    public static AdminStudent insert(CreateStudentDto student) throws SQLException {
        String addSql = "INSERT INTO tbl_students(first_name,last_name,username,email,password,salt) VALUES (?,?,?,?,?,?)";
        Connection connection = ConnectionUtil.getConnection();
        PreparedStatement statement = connection.prepareStatement(addSql);



        statement.setString(1, student.getFirstName());
        statement.setString(2, student.getLastName());
        statement.setString(3, student.getUsername());
        statement.setString(4, student.getEmail());
        statement.setString(5, student.getPassword());
        statement.setString(6,student.getSalt());


        statement.executeUpdate();
        return AdminStudentRepository.getByUsername(student.getUsername());

    }

    // fshirja e nje studenti nga databaza

    public static boolean deleteStudent(CreateStudentDto student) throws SQLException {
        String deleteSql = "DELETE FROM tbl_students WHERE username = ?";
        Connection connection = ConnectionUtil.getConnection();

        PreparedStatement deleteStatement = connection.prepareStatement(deleteSql);
        deleteStatement.setString(1, student.getUsername());
        int rowsAffected = deleteStatement.executeUpdate();

        deleteStatement.close();
        connection.close();

        return rowsAffected > 0;


    }

    // perditesimi-editimi i nje studenti

    public static AdminStudent updateStudent(UpdateStudentDto student) throws SQLException {
        String updateSql = "UPDATE tbl_students SET first_name=?, last_name=?, username=?, email=? ,password=? WHERE id=?";
        Connection connection = ConnectionUtil.getConnection();

        PreparedStatement statement = connection.prepareStatement(updateSql);
        //statement.setInt(1, student.getId());
        statement.setString(1, student.getFirst_name());
        statement.setString(2, student.getLast_name());
        statement.setString(3, student.getUsername());
        statement.setString(4, student.getEmail());
        statement.setString(5,student.getPassword());
        statement.setString(6,student.getSalt());


        statement.executeUpdate();
        statement.close();

        return AdminStudentRepository.getByUsername(student.getUsername());
    }




    public static AdminStudent getByUsername(String username) throws SQLException {
        String sql = "SELECT * FROM tbl_students WHERE username=?";
        try (Connection connection = ConnectionUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, username);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                int id = resultSet.getInt("id");
                String firstName = resultSet.getString("first_name");
                String lastName = resultSet.getString("last_name");
                String email=resultSet.getString("email");
                String password=resultSet.getString("password");
                return new AdminStudent(id,firstName,lastName,username,email,password);
            } else {
                return null;
            }
        }
    }
}



