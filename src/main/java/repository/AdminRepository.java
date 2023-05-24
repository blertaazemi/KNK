package repository;

import models.Student;
import models.dto.CreateAdminDto;
import models.dto.CreateStudentDto;
import service.ConnectionUtil;
import service.PasswordHasher;

import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Base64;

public class AdminRepository {
    public void insert(CreateAdminDto admin, Connection connection) throws SQLException {

        String insertData = "INSERT INTO tbl_admin "
                + "(first_name, last_name, username, email, password, salt, role)"
                + "VALUES(?, ?, ?, ?, ?, ?, ?)";
        PreparedStatement prepareStatement = connection.prepareStatement(insertData);
        prepareStatement.setString(1, admin.getFirstName());
        prepareStatement.setString(2, admin.getLastName());
        prepareStatement.setString(3, admin.getUsername());
        prepareStatement.setString(4, admin.getEmail());
        prepareStatement.setString(5, admin.getSalt());
        prepareStatement.setString(6, admin.getPassword());
        prepareStatement.setString(7, "admin");

        prepareStatement.executeUpdate();
    }


    public static String getSalt(String username)throws SQLException, NoSuchAlgorithmException{
        Connection connection = ConnectionUtil.getConnection();
        String sql = "SELECT * FROM tbl_admin where username = ?";
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
        String sql = "Select * from tbl_admin where username = ?";
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
    public boolean checkUsernameExists(String username, Connection connection) throws SQLException {
        String sql = "SELECT * FROM tbl_admin WHERE username = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, username);
        ResultSet resultSet = preparedStatement.executeQuery();
        return resultSet.next();
    }

    public boolean checkEmailExists(String email, Connection connection) throws SQLException {
        String sql = "SELECT * FROM tbl_admin WHERE email = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, email);
        ResultSet resultSet = preparedStatement.executeQuery();
        return resultSet.next();
    }
}


