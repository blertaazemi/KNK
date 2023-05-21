package repository;

import models.Student;
import service.ConnectionUtil;
import service.PasswordHasher;

import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Base64;

public class StudentRepository {
    public void createStudent(Student student) throws SQLException {
        Connection connection = null;
        PreparedStatement prepareStatement = null;
        ResultSet resultSet = null;

        try {
            connection = ConnectionUtil.getConnection();

            String checkUsername = "SELECT * FROM tbl_students WHERE username = ?";
            prepareStatement = connection.prepareStatement(checkUsername);
            prepareStatement.setString(1, student.getUsername());
            resultSet = prepareStatement.executeQuery();

            if (resultSet.next()) {
                throw new SQLException("Perdoruesi " + student.getUsername() + " nuk eshte i lire per perdorim.");
            } else {
                String salt = PasswordHasher.generateSalt();
                String saltedHash = PasswordHasher.generateSaltedHash(student.getPassword(), salt);

                String insertData = "INSERT INTO tbl_students "
                        + "(first_name, last_name, username, email, password, salt, role)"
                        + "VALUES(?, ?, ?, ?, ?, ?, ?)";
                prepareStatement = connection.prepareStatement(insertData);
                prepareStatement.setString(1, student.getFirst_name());
                prepareStatement.setString(2, student.getLast_name());
                prepareStatement.setString(3, student.getUsername());
                prepareStatement.setString(4, student.getEmail());
                prepareStatement.setString(5, saltedHash);
                prepareStatement.setString(6, Base64.getEncoder().encodeToString(salt.getBytes()));
                prepareStatement.setString(7, "student");

                prepareStatement.executeUpdate();
            }
        } finally {
            // Close the resources in the reverse order of their creation
            if (resultSet != null) {
                resultSet.close();
            }
            if (prepareStatement != null) {
                prepareStatement.close();
            }
            if (connection != null) {
                connection.close();
            }
        }
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
}

