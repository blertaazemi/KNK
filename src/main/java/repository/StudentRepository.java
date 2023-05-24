package repository;

import models.Student;
import models.dto.CreateStudentDto;
import service.ConnectionUtil;
import service.PasswordHasher;

import java.security.NoSuchAlgorithmException;
import java.sql.*;
import java.util.Base64;

public class StudentRepository {
    public void insert(CreateStudentDto student, Connection connection) throws SQLException {

                String insertData = "INSERT INTO tbl_students "
                        + "(first_name, last_name, username, email, password, salt, role)"
                        + "VALUES(?, ?, ?, ?, ?, ?, ?)";
                PreparedStatement prepareStatement = connection.prepareStatement(insertData);
                prepareStatement.setString(1, student.getFirstName());
                prepareStatement.setString(2, student.getLastName());
                prepareStatement.setString(3, student.getUsername());
                prepareStatement.setString(4, student.getEmail());
                prepareStatement.setString(5, student.getSalt());
                prepareStatement.setString(6, student.getPassword());
                prepareStatement.setString(7, "student");

                prepareStatement.executeUpdate();
            }


    public static String getSalt(String username)throws SQLException, NoSuchAlgorithmException{
        Connection connection = ConnectionUtil.getConnection();
        String sql = "SELECT * FROM tbl_students where username = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1,username);
        ResultSet resultSet=preparedStatement.executeQuery();
        if(resultSet.next()){
            String salt = resultSet.getString("salt");
            return salt;
        }else{
            return null;
        }
    }

    public static String getPassword(String username) throws SQLException {
        Connection connection = ConnectionUtil.getConnection();
        String sql = "Select * from tbl_students where username = ?";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, username);
        ResultSet resultSet = statement.executeQuery();
        if(resultSet.next()){
            String password = resultSet.getString("password");
            return password;
        }
        else{
            return null;
        }
    }
    public Student GetUser( String username) throws SQLException, NoSuchAlgorithmException {
        Connection connection = ConnectionUtil.getConnection();
        Student student =  null;
        String sql = "SELECT * FROM tbl_students WHERE username=?";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, username);
        ResultSet result = statement.executeQuery();
        while (result.next()) {
            String first_name = result.getString("first_name");
            String last_name = result.getString("last_name");
            String Username = result.getString("username");
            String email = result.getString("email");

            student = new Student(first_name, last_name, username, email, "", "");

        }
        return student;
    }
    public boolean checkUsernameExists(String username, Connection connection) throws SQLException {
        String sql = "SELECT * FROM tbl_students WHERE username = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, username);
        ResultSet resultSet = preparedStatement.executeQuery();
        return resultSet.next();
    }

    public boolean checkEmailExists(String email, Connection connection) throws SQLException {
        String sql = "SELECT * FROM tbl_students WHERE email = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, email);
        ResultSet resultSet = preparedStatement.executeQuery();
        return resultSet.next();
    }
}

